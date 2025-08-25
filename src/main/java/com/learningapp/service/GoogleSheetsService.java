package com.learningapp.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.learningapp.constants.Constants;
import com.learningapp.entity.StudyModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Service
public class GoogleSheetsService {

    @Value("${spreadsheetId}")
    private String spreadsheetId;

    private final Sheets sheetsService;

    private final String range = "!A1:Z";

    @Autowired
    public GoogleSheetsService(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    public List<StudyModule> getAllStudyModules() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, Constants.TABLE.STUDY_MODULES + range)
                .execute();
        List<List<Object>> values = response.getValues();
        List<StudyModule> studyModules = new ArrayList<>();

        if (values != null) {
            for (int i = 1; i < values.size(); i++) {
                List<Object> row = values.get(i);
                StudyModule studyModule = new StudyModule();
                studyModule.setId(row.get(0).toString());
                studyModule.setUserId(row.get(1).toString());
                studyModule.setName(row.get(2).toString());
                studyModule.setDescription(row.size() > 3 ? row.get(3).toString() : null);
                studyModules.add(studyModule);
            }
        }
        return studyModules;
    }

    public <T> List<T> getAllRecords(String sheetName, Function<List<Object>, T> mapper)
            throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, sheetName + range)
                .execute();
        List<List<Object>> values = response.getValues();

        List<Map<String, Object>> maps = new ArrayList<>();
        List<Object> headers = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            List<Object> row = values.get(i);
            Map<String, Object> rowMap = new HashMap<>();
            for (int k = 0; k < headers.size() && k < row.size(); k++) {
                rowMap.put(headers.get(k).toString(), row.get(k));
            }
            maps.add(rowMap);
        }
        List<T> records = new ArrayList<>();

        if (!CollectionUtils.isEmpty(values)) {
            for (int i = 1; i < values.size(); i++) {
                List<Object> row = values.get(i);
                T record = mapper.apply(row);
                records.add(record);
            }
        }
        return records;
    }

    public void addStudyModule(StudyModule studyModule) throws IOException {
        List<Object> row = Arrays.asList(
                studyModule.getId(),
                studyModule.getUserId(),
                studyModule.getName(),
                studyModule.getDescription()
        );
        ValueRange body = new ValueRange().setValues(Arrays.asList(row));
        sheetsService.spreadsheets().values()
                .append(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
    }

    public void updateStudyModule(Long id, StudyModule updatedStudyModule) throws IOException {
        List<List<Object>> values = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute()
                .getValues();

        if (values != null) {
            for (int i = 1; i < values.size(); i++) {
                if (values.get(i).get(0).toString().equals(id.toString())) {
                    List<Object> row = Arrays.asList(
                            id.toString(),
                            updatedStudyModule.getUserId(),
                            updatedStudyModule.getName(),
                            updatedStudyModule.getDescription()
                    );
                    ValueRange body = new ValueRange().setValues(Arrays.asList(row));
                    sheetsService.spreadsheets().values()
                            .update(spreadsheetId, "Sheet1!A" + (i + 1) + ":D" + (i + 1), body)
                            .setValueInputOption("RAW")
                            .execute();
                    break;
                }
            }
        }
    }

}
package com.learningapp.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.learningapp.entity.StudyModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleSheetsService {

    private final Sheets sheetsService;
    private final String spreadsheetId = "1p-aM5oNEd07SNh4gqaXHgy3Hlig_9rFjzNWfa8hRRk8"; // Thay bằng ID của Google Sheet
    private final String range = "Sheet1!A1:D"; // Phạm vi dữ liệu (ví dụ: Sheet1, cột A đến C)

    @Autowired
    public GoogleSheetsService(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    // Lấy tất cả study modules
    public List<StudyModule> getAllStudyModules() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        List<StudyModule> studyModules = new ArrayList<>();

        if (values != null) {
            // Bỏ qua dòng đầu tiên nếu nó là tiêu đề
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

    // Thêm một study module mới
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

    // Cập nhật study module
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

    // Xóa study module
    public void deleteStudyModule(Long id) throws IOException {
        List<List<Object>> values = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute()
                .getValues();

        if (values != null) {
            for (int i = 1; i < values.size(); i++) {
                if (values.get(i).get(0).toString().equals(id.toString())) {
                    // Xóa hàng bằng cách gửi yêu cầu cập nhật với giá trị rỗng
                    ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList("", "", "", "")));
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
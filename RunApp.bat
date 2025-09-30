@echo off
echo Starting build process...

:: Chạy lệnh mvn clean package với tùy chọn -DskipTests
call mvn clean package -DskipTests

:: Kiểm tra xem lệnh mvn có thành công không
if %ERRORLEVEL% neq 0 (
    echo Maven build failed!
    pause
    exit /b %ERRORLEVEL%
)

:: Tìm file .jar trong thư mục target
for %%F in (target\*.jar) do (
    set "JAR_FILE=%%F"
    goto :found
)
:found

:: Kiểm tra xem file .jar có tồn tại không
if not defined JAR_FILE (
    echo No JAR file found in target directory!
    pause
    exit /b 1
)

:: Copy file .jar sang thư mục gốc
copy "%JAR_FILE%" ap.jar
if %ERRORLEVEL% neq 0 (
    echo Failed to copy JAR file!
    pause
    exit /b %ERRORLEVEL%
)

:: Chạy file ap.jar
echo Running JAR file...
java -jar ap.jar

:: Kiểm tra xem lệnh java có thành công không
if %ERRORLEVEL% neq 0 (
    echo Failed to run JAR file!
    pause
    exit /b %ERRORLEVEL%
)

echo Execution completed successfully.
pause
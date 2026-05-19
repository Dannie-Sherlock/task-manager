@echo off
echo ========================================
echo 任务管理器启动脚本
echo ========================================
echo.

cd /d "%~dp0"

echo 正在检查Java版本...
java -version

echo.
echo 正在启动任务管理器应用...
echo.

"C:\maven\apache-maven-3.9.15\bin\mvn.cmd" spring-boot:run

pause
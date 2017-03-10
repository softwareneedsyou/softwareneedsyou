cd /d "%~dp0"
cd ..
mklink .git\hooks\pre-commit scripts\pre-commit.bat
pause

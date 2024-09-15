@echo off
MODE con: COLS=90 LINES=28
title Loading
rem 自动提权以管理员方式运行
%1 Start "" Mshta Vbscript:createobject("Shell.Application").Shellexecute("""%~0""","::",,"Runas",1)(Window.close)&Exit

@echo off
MODE con: COLS=90 LINES=30
setlocal

:Start
title 禁止/恢复 Windows 10/11更新工具
cls
echo.
echo.
echo.
echo.
echo.=========================    禁止/恢复 Windows 10/11更新工具    ==========================
echo.
echo.
echo.  禁用 Windows 更新服务会影响自带驱动程序更新、Microsoft Store 及Xbox 游戏
echo.  的正常运行。
echo.
echo.  该工具利用 Windows 自带的暂停更新选项，直接暂停更新1000周，以实现“屏蔽更
echo.  新”功能，但不影响其他功能正常使用，随时可使用该工具恢复正常更新。
echo.
echo.
echo.                     COAadmin
echo.
echo.              Blog：www.zifumao.com
echo.
echo.   请选择你的操作：
echo.
echo.    [1] 暂停至2099年
echo.
echo.    [2] 恢复正常更新
echo.
echo.    [3] 退出工具
echo.
echo.
echo.
set /p choice=请输入选项号码：

IF "%choice%"=="1" (
  goto Disable
) ELSE IF "%choice%"=="2" (
  goto Enable
) ELSE IF "%choice%"=="3" (
  exit
) ELSE (
  echo. 
  echo.   无效的选项，请重新输入。
  echo. 
  goto Start
)


:Disable
title 暂停Windows 10/11 更新1000周
echo.
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "FlightSettingsMaxPauseDays" /t REG_DWORD /d 27740 /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseFeatureUpdatesStartTime" /t REG_SZ /d "2023-07-07T10:00:52Z" /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseFeatureUpdatesEndTime" /t REG_SZ /d "2099-01-01T09:59:52Z" /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseQualityUpdatesStartTime" /t REG_SZ /d "2023-07-07T10:00:52Z" /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseQualityUpdatesEndTime" /t REG_SZ /d "2099-01-01T09:59:52Z" /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseUpdatesStartTime" /t REG_SZ /d "2023-07-07T09:59:52Z" /f
reg add "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /v "PauseUpdatesExpiryTime" /t REG_SZ /d "2099-01-01T09:59:52Z" /f
echo.
goto end


:Enable
title 恢复Windows 10/11 正常更新
echo.
reg delete "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings" /f
echo.
goto end

:End
echo.
echo.  操作完成，按任意键返回！
pause>nul

goto Start
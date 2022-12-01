@SETCONSOLE /hide
@set PATH_TO_FX="C:\Program Files\BlueJ\lib\javafx\lib"
@set PATH_TO_JAVA="C:\Program Files\BlueJ\jdk\bin"
@if NOT exist "%PATH_TO_JAVA%" GOTO NOJAVA
@if NOT exist "%PATH_TO_FX%" GOTO NOFX

@echo Oeffne Graphentester mit Java bei %PATH_TO_JAVA%
@echo und JavaFX bei %PATH_TO_FX%.

%PATH_TO_JAVA%\java --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.media,javafx.web,javafx.fxml,javafx.swing -jar graphentester.jar
@GOTO ENDE

:NOFX
@echo Der JavaFX-Pfad %PATH_TO_FX% ist nicht richtig. 
@echo Bitte passen Sie dieses Verzeichnis in der bat-Datei an.
@GOTO ENDE

:NOJAVA
@echo Der Java-Pfad %PATH_TO_JAVA% ist nicht richtig. 
@echo Bitte passen Sie dieses Verzeichnis in der bat-Datei an.

:ENDE
@pause
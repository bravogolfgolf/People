package ui;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Objects;

class CustomFileFilter extends FileFilter {
    private static final String PERSON_DATABASE_FILE_EXTENSION = "per";
    private static final String PERSON_DATABASE_FILE_EXTENSION_DESC = "Person database files (*.per)";

    @Override
    public boolean accept(File file) {
        return hasAcceptableExtension(file) || file.isDirectory();
    }

    private boolean hasAcceptableExtension(File file) {
        return Objects.equals(
                FileFilterUtility.getExtensionOf(file),
                PERSON_DATABASE_FILE_EXTENSION);
    }

    @Override
    public String getDescription() {
        return PERSON_DATABASE_FILE_EXTENSION_DESC;
    }
}

class FileFilterUtility {

    private static int index;
    private static String fileName;

    static String getExtensionOf(File file) {
        fileName = file.getName();
        index = fileName.lastIndexOf(".");

        return hasNoPeriodInString() || hasPeriodAtEndOfString() ?
                null :
                fileExtension();
    }

    private static String fileExtension() {
        return fileName.substring(index - 1, fileName.length());
    }

    private static boolean hasPeriodAtEndOfString() {
        return index == (fileName.length() - 1);
    }

    private static boolean hasNoPeriodInString() {
        return index == -1;
    }
}

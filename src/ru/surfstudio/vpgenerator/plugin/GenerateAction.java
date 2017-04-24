package ru.surfstudio.vpgenerator.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 */
public class GenerateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiElement a = e.getData(LangDataKeys.PSI_ELEMENT);
        if (!(a instanceof PsiDirectory)) {
            return;
        }

        JavaDirectoryService.getInstance().createClass((PsiDirectory) a, "Presenter");
        JavaDirectoryService.getInstance().createClass((PsiDirectory) a, "View");
        JavaDirectoryService.getInstance().createInterface((PsiDirectory) a, "Component");
    }
}

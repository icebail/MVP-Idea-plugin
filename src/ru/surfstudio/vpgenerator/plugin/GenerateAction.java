package ru.surfstudio.vpgenerator.plugin;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;

/**
 */
public class GenerateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        PsiElement psiDirectory = actionEvent.getData(LangDataKeys.PSI_ELEMENT);
        if (!(psiDirectory instanceof PsiDirectory)) {
            return;
        }

        TemplateManager tm = new TemplateManager("Hello");

        createPresenterFile(tm, actionEvent.getProject(), (PsiDirectory) psiDirectory);
        createViewFile(tm, actionEvent.getProject(), (PsiDirectory) psiDirectory);
        createComponentFile(tm, actionEvent.getProject(), (PsiDirectory) psiDirectory);
    }

    private void createPresenterFile(TemplateManager tm, Project project, PsiDirectory psiDirectory) {
        FileTemplate template = FileTemplateManager.getInstance(project)
                .addTemplate(TemplateManager.PRESENTER_TEMPLATE_NAME, "java");
        template.setText(TemplateManager.PRESENTER_TEMPLATE_TEXT);

        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getPresenterName(),
                TemplateManager.PRESENTER_TEMPLATE_NAME,
                true,
                tm.getPresenterMap());
    }

    private void createViewFile(TemplateManager tm, Project project, PsiDirectory psiDirectory) {
        FileTemplate template = FileTemplateManager.getInstance(project)
                .addTemplate(TemplateManager.VIEW_TEMPLATE_NAME, "java");
        template.setText(TemplateManager.VIEW_TEMPLATE_TEXT);

        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getViewName(),
                TemplateManager.VIEW_TEMPLATE_NAME,
                true,
                tm.getViewMap());
    }

    private void createComponentFile(TemplateManager tm, Project project, PsiDirectory psiDirectory) {
        FileTemplate template = FileTemplateManager.getInstance(project)
                .addTemplate(TemplateManager.COMPONENT_TEMPLATE_NAME, "java");
        template.setText(TemplateManager.COMPONENT_TEMPLATE_TEXT);

        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getComponentName(),
                TemplateManager.COMPONENT_TEMPLATE_NAME,
                true,
                tm.getComponentMap());
    }

}

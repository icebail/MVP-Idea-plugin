package ru.surfstudio.vpgenerator.plugin;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
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

        Project project = actionEvent.getProject();

        String name = Messages.showInputDialog(actionEvent.getProject(),
                "Enter screen name",
                "MVP name", Messages.getQuestionIcon());

        TemplateManager tm = new TemplateManager(name);

        createPresenterFile(tm, project, (PsiDirectory) psiDirectory);
        createViewFile(tm, project, (PsiDirectory) psiDirectory);
        createComponentFile(tm, project, (PsiDirectory) psiDirectory);
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

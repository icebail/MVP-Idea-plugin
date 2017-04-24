package ru.surfstudio.vpgenerator.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
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

        TemplateComponent templateComponent = ApplicationManager.getApplication()
                .getComponent(TemplateComponent.class);

        Project project = actionEvent.getProject();
        templateComponent.createTemplateIfNeed(project);

        String name = Messages.showInputDialog(actionEvent.getProject(),
                "Enter screen name",
                "MVP name", Messages.getQuestionIcon());

        TemplateManager tm = new TemplateManager(name);

        createPresenterFile(tm, (PsiDirectory) psiDirectory);
        createViewFile(tm, (PsiDirectory) psiDirectory);
        createComponentFile(tm, (PsiDirectory) psiDirectory);
    }

    private void createPresenterFile(TemplateManager tm, PsiDirectory psiDirectory) {
        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getPresenterName(),
                TemplateManager.PRESENTER_TEMPLATE_NAME,
                true,
                tm.getPresenterMap());
    }

    private void createViewFile(TemplateManager tm, PsiDirectory psiDirectory) {
        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getViewName(),
                TemplateManager.VIEW_TEMPLATE_NAME,
                true,
                tm.getViewMap());
    }

    private void createComponentFile(TemplateManager tm, PsiDirectory psiDirectory) {
        JavaDirectoryService.getInstance().createClass(psiDirectory,
                tm.getComponentName(),
                TemplateManager.COMPONENT_TEMPLATE_NAME,
                true,
                tm.getComponentMap());
    }

}

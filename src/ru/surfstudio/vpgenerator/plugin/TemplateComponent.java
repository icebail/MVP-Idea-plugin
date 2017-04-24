package ru.surfstudio.vpgenerator.plugin;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by vbeglyanin on 24.04.17.
 */
public class TemplateComponent implements ApplicationComponent {
    public TemplateComponent() {
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    public void createTemplateIfNeed(Project project) {
        FileTemplateManager manager = FileTemplateManager.getInstance(project);

        if (manager.getTemplate(TemplateManager.VIEW_TEMPLATE_NAME) == null) {
            FileTemplate template = manager.addTemplate(TemplateManager.VIEW_TEMPLATE_NAME, "java");
            template.setText(TemplateManager.VIEW_TEMPLATE_TEXT);
        }

        if (manager.getTemplate(TemplateManager.COMPONENT_TEMPLATE_NAME) == null) {
            FileTemplate template = manager
                    .addTemplate(TemplateManager.COMPONENT_TEMPLATE_NAME, "java");
            template.setText(TemplateManager.COMPONENT_TEMPLATE_TEXT);
        }

        if (manager.getTemplate(TemplateManager.PRESENTER_TEMPLATE_NAME) == null) {
            FileTemplate template = manager
                    .addTemplate(TemplateManager.PRESENTER_TEMPLATE_NAME, "java");
            template.setText(TemplateManager.PRESENTER_TEMPLATE_TEXT);
        }
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "TemplateComponent";
    }
}

package ru.surfstudio.vpgenerator.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vbeglyanin on 24.04.17.
 */
class TemplateManager {

    static final String PRESENTER_TEMPLATE_NAME = "ru.surfstudio.templates.presenter";
    static final String VIEW_TEMPLATE_NAME = "ru.surfstudio.templates.view";
    static final String COMPONENT_TEMPLATE_NAME = "ru.surfstudio.templates.component";

    private static final String VIEW_POSTFIX = "View";
    private static final String PRESENTER_POSTFIX = "Presenter";
    private static final String COMPONENT_POSTFIX = "Component";

    static final String PRESENTER_TEMPLATE_TEXT =
            "package ${PACKAGE_NAME}; public class ${NAME} extends BasePresenter<${ViewClass}> {" +
                    "}";

    static final String VIEW_TEMPLATE_TEXT =
            "import android.os.Bundle; \nimport javax.inject.Inject; \n" +
                    "public class ${NAME} extends BaseActivityView {\n" +
                    "    @Inject\n" +
                    "    ${PresenterClass} presenter;\n" +
                    "\n" +
                    "    @Override\n" +
                    "    protected void onCreate(Bundle savedInstanceState) {\n" +
                    "        super.onCreate(savedInstanceState);\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    protected int getContentView() {\n" +
                    "        return 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public ${PresenterClass} getPresenter() {\n" +
                    "        return presenter;\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public ScreenComponent createScreenComponent() {\n" +
                    "        return Dagger${ComponentClass}\n" +
                    "                .builder()\n" +
                    "                .appComponent(getApplicationComponent())\n" +
                    "                .build();\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public String getName() {\n" +
                    "        return ${NAME}.class.getSimpleName();\n" +
                    "    }\n" +
                    "}";

    static final String COMPONENT_TEMPLATE_TEXT =
            "import dagger.Component;\n" +
            "\n" +
            "@PerScreen\n" +
            "@Component(dependencies = {AppComponent.class}, modules = ActivityModule.class)\n" +
            "interface ${NAME} extends ScreenComponent<$ViewClass> {\n" +
            "}";

    private String name;

    TemplateManager(String name) {
        this.name = name;
    }

    Map<String, String> getPresenterMap() {
        Map<String, String> result = new HashMap<>();
        result.put("ViewClass", name + VIEW_POSTFIX);
        return result;
    }

    String getPresenterName() {
        return name + PRESENTER_POSTFIX;
    }

    String getViewName() {
        return name + VIEW_POSTFIX;
    }

    String getComponentName() {
        return name + COMPONENT_POSTFIX;
    }

    Map<String, String> getViewMap() {
        Map<String, String> result = new HashMap<>();
        result.put("PresenterClass", getPresenterName());
        result.put("ComponentClass", getComponentName());
        return result;
    }

    Map<String, String> getComponentMap() {
        Map<String, String> result = new HashMap<>();
        result.put("ViewClass", getViewName());
        return result;
    }
}

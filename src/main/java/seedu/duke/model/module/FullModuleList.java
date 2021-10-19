package seedu.duke.model.module;

import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.JsonUtil;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.exceptions.ModuleNotFoundException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to hold the complete list of modules found on NUSMods loaded from ModuleInfo.json. This list
 * cannot be manipulated.
 */
public class FullModuleList {
    private List<Module> fullModuleList;
    private Map<String, Module> moduleMap;

    public FullModuleList() throws ModuleLoadException, FileNotFoundException {
        loadModules();
    }

    private void loadModules() throws ModuleLoadException, FileNotFoundException {
        JsonUtil moduleLoader = new JsonUtil();
        Module[] arrayOfModules = moduleLoader.loadModulesFromJson();
        fullModuleList = Arrays.asList(arrayOfModules);
        setModuleMap();
    }

    private void setModuleMap() {
        moduleMap = new HashMap<>();
        for (int i = 0; i < fullModuleList.size(); i++) {
            moduleMap.put(fullModuleList.get(i).getModuleCode(), fullModuleList.get(i));
        }
    }

    public List<Module> getFullModuleList() {
        return fullModuleList;
    }

    /**
     * Locates the Module object matching a module code. The provided module code is checked against a hashmap
     * matching module codes to their corresponding Module objects.
     *
     * @param moduleCode the code of the module to locate (eg. CS2113T)
     * @return the Module object matching the code
     * @throws ModuleNotFoundException the exception thrown when there is no corresponding module found on NUSMods
     */
    public Module findModule(String moduleCode) throws ModuleNotFoundException {
        if (!moduleMap.containsKey(moduleCode)) {
            throw new ModuleNotFoundException(Messages.ERROR_MODULE_NOT_FOUND);
        }
        return moduleMap.get(moduleCode);
    }

    public String getModulesFull(ModuleList moduleList) throws DukeException {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < moduleList.getSize(); i++) {
            Module module = moduleList.getModule(i);
            module = findModule(module.getModuleCode());
            s.append(module.getFullInfo());
        }
        return s.toString();
    }
}

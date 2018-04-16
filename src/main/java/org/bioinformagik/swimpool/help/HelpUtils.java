package org.bioinformagik.swimpool.help;

import org.apache.commons.lang3.tuple.Pair;
import org.broadinstitute.hellbender.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: documentation
 *
 * @author Daniel Gomez-Sanchez (magicDGS)
 */
// TODO: we should set up a way to make printSettings configurable in the CLP without overriding tools
// TODO: and printLibraryVersions too (this might actually fail completely for example, with Picard)
public final class HelpUtils {

    // Mapping from base class for command line tool and suffix marking the procedence of the tool
    // for example, Picard/GATK
    private static final Map<Class<?>, String> TOOL_CLASS_TO_SUFFIX = createToolSuffixes();

    private static Map<Class<?>, String> createToolSuffixes() {
        final Map<Class<?>, String> map = new HashMap<>();
        map.put(picard.cmdline.CommandLineProgram.class, " (Picard)");
        map.put(org.broadinstitute.hellbender.cmdline.CommandLineProgram.class, " (GATK)");
        return map;
    }

    private static Map<String, Pair<String, String>> createDeprecatedRegistry() {
        return null;
    }

    // cannot be instantiated
    private HelpUtils() {}

    /**
     * Given a Class that is a CommandLineProgram, either GATK or Picard, return a display name suitable for
     * presentation to the user that distinguishes GATK tools from Picard tools by including a " (Picard)" suffix;
     * @param toolClass A CommandLineProgram class object may not be null
     * @return tool display name
     */
    public static String toolDisplayName(final Class<?> toolClass) {
        Utils.nonNull(toolClass, "A valid class is required to get a display name");
        // get the simple tool name
        final String toolName = toolClass.getSimpleName();
        for (Map.Entry<Class<?>, String> entry: TOOL_CLASS_TO_SUFFIX.entrySet()) {
            // if the class is assignable for it, add a suffix to it
            if (entry.getKey().isAssignableFrom(toolClass)) {
                return toolName + entry.getValue();
            }
        }
        // if none is found, return the simple tool name
        return toolName;
    }
}

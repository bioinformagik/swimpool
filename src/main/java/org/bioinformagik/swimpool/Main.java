package org.bioinformagik.swimpool;

import org.bioinformagik.swimpool.help.HelpUtils;
import org.broadinstitute.hellbender.cmdline.CommandLineProgram;
import org.broadinstitute.hellbender.tools.IndexFeatureFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO: document
 *
 * @author Daniel Gomez-Sanchez (magicDGS)
 */
// TODO: we should add a custom configuration with a custom package for codecs
// TODO: if we would like to hide some configuration, check the methods:
// TODO: parseArgsForConfigSetup/setupConfigAndExtractProgram
public final class Main extends org.broadinstitute.hellbender.Main {

    /** The entry point to SwimPool from commandline.
     *
     * <p>It calls {@link #mainEntry(String[])} from this instance. */
    public static void main(final String[] args) {
        new Main().mainEntry(args);
    }

    /**
     * SwimPool only includes all the tools in its {@link org.bioinformagik.swimpool.tools}.
     */
    @Override
    protected List<String> getPackageList() {
        return Collections.singletonList("org.bioinformagik.swimpool.tools");
    }

    /**
     * SwimPool includes some general tools for the bundled GATK:
     *
     * <ul>
     *     <li>
     *         {@link org.broadinstitute.hellbender.tools.IndexFeatureFile} for indexing any
     *         custom feature source handled by SwimPool.
     *     </li>
     * </ul>
     */
    @Override
    protected List<Class<? extends CommandLineProgram>> getClassList() {
        final List<Class<? extends CommandLineProgram>> classList = new ArrayList<>();
        // TODO: add more classes of general purposes
        // TODO: GATK does not have any way to add single Picard tools (I believe)
        // TODO: and indexing BAM files or create sequence dictionaries may be good to have
        classList.addAll(Arrays.asList(
                IndexFeatureFile.class
        ));
        return classList;
    }

    /**
     * Get the display name for the tool.
     *
     * @return A display name to be used for the tool who's class is {@code clazz}.
     */
    protected String getDisplayNameForToolClass(final Class<?> clazz) {
        return HelpUtils.toolDisplayName(clazz);
    }

    /**
     * Get deprecation message for a tool.
     *
     * <p>Currently, SwimPool does not have any deprecation.
     *
     * @param toolName command specified by the user.
     *
     * @return deprecation message string, or null if none.
     */
    public String getToolDeprecationMessage(final String toolName) {
        return null;
    }
}

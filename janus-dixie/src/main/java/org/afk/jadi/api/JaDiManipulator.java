package org.afk.jadi.api;

/**
 * This interface describes a manipulator of configuration. The manipulator is a
 * user interface that allows modification of configuration values. Created by
 * axel on 28.10.15.
 */
public interface JaDiManipulator {

    /**
     * This method is called whenever a new configuration was added.
     *
     * @param <T> The type of the configuration value.
     * @param recordSet The JaDiRecordSet that provides all information of the
     * config.
     */
    <T> void register(JaDiRecordSet<T> recordSet);

}

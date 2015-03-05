// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.database.conn;

/**
 * These constants are the keys of map
 * {@link org.talend.core.model.metadata.builder.connection.DatabaseConnection#getParameters <em>Parameters</em>} of
 * database connection. The key of a parameter you want to add to database connection later is put here to manager
 * easily.
 * 
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 8, 2012
 */
public class ConnParameterKeys {

    /**
     * DB connection keys.
     */
    public static final String CONN_PARA_KEY_DB_TYPE = "CONN_PARA_KEY_DB_TYPE"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_DB_PRODUCT = "CONN_PARA_KEY_DB_PRODUCT"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_DB_PORT = "CONN_PARA_KEY_DB_PORT"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_DB_SERVER = "CONN_PARA_KEY_DB_SERVER"; //$NON-NLS-1$

    /**
     * Hadoop common keys
     */
    public static final String CONN_PARA_KEY_HADOOP_CLUSTER_ID = "CONN_PARA_KEY_HADOOP_CLUSTER_ID"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HADOOP_CUSTOM_JARS = "CONN_PARA_KEY_HADOOP_CUSTOM_JARS"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_NAME_NODE_URL = "CONN_PARA_KEY_NAME_NODE_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_JOB_TRACKER_URL = "CONN_PARA_KEY_JOB_TRACKER_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_USE_YARN = "CONN_PARA_KEY_USE_YARN"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_USE_KRB = "CONN_PARA_KEY_USE_KRB"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_USEKEYTAB = "CONN_PARA_KEY_USEKEYTAB";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_KEYTAB_PRINCIPAL = "CONN_PARA_KEY_KEYTAB_PRINCIPLA";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_KEYTAB = "CONN_PARA_KEY_KEYTAB";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_NAME_NODE_PRINCIPAL = "CONN_PARA_KEY_NAME_NODE_PRINCIPAL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_JOB_TRACKER_PRINCIPAL = "CONN_PARA_KEY_JOB_TRACKER_PRINCIPAL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_USERNAME = "CONN_PARA_KEY_USERNAME"; //$NON-NLS-1$

    /**
     * Hive keys.
     */
    public static final String CONN_PARA_KEY_HIVE_MODE = "CONN_PARA_KEY_HIVE_MODE"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_DISTRIBUTION = "CONN_PARA_KEY_HIVE_DISTRIBUTION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_VERSION = "CONN_PARA_KEY_HIVE_VERSION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_CHARACTER_ENCODING = "CONN_PARA_KEY_CHARACTER_ENCODING"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_URL = "CONN_PARA_KEY_METASTORE_CONN_URL"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_USERNAME = "CONN_PARA_KEY_METASTORE_CONN_USERNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_PASSWORD = "CONN_PARA_KEY_METASTORE_CONN_PASSWORD"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_JAR"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME = "CONN_PARA_KEY_METASTORE_CONN_DRIVER_NAME"; //$NON-NLS-1$

    public static final String HIVE_SERVER_VERSION = "HIVE_SERVER_VERSION";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_PROPERTIES = "CONN_PARA_KEY_HIVE_PROPERTIES"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HIVE_JDBC_PROPERTIES = "CONN_PARA_KEY_HIVE_JDBC_PROPERTIES"; //$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_HIVEPRINCIPLA = "HIVE_AUTHENTICATION_HIVEPRINCIPLA";//$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_METASTOREURL = "HIVE_AUTHENTICATION_METASTOREURL";//$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_DRIVERJAR_PATH = "HIVE_AUTHENTICATION_DRIVERJAR_PATH";//$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_DRIVERCLASS = "HIVE_AUTHENTICATION_DRIVERCLASS";//$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_USERNAME = "HIVE_AUTHENTICATION_USERNAME";//$NON-NLS-1$

    public static final String HIVE_AUTHENTICATION_PASSWORD = "HIVE_AUTHENTICATION_PASSWORD";//$NON-NLS-1$

    /**
     * The key is for metastore server.
     */
    public static final String CONN_PARA_KEY_METASTORE_SERVERNAME = "CONN_PARA_KEY_METASTORE_SERVERNAME"; //$NON-NLS-1$

    /**
     * The key is for metastore server port.
     */
    public static final String CONN_PARA_KEY_METASTORE_PROT = "CONN_PARA_KEY_METASTORE_PROT"; //$NON-NLS-1$

    /*********** HD Insight keys ***************/
    public static final String CONN_PARA_KEY_WEB_HCAT_HOSTNAME = "CONN_PARA_KEY_WEB_HCAT_HOSTNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_WEB_HCAT_PORT = "CONN_PARA_KEY_WEB_HCAT_PORT"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_WEB_HCAT_USERNAME = "CONN_PARA_KEY_WEB_HCAT_USERNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_WEB_HCAT_JOB_RESULT_FOLDER = "CONN_PARA_KEY_WEB_HCAT_JOB_RESULT_FOLDER"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HDI_USERNAME = "CONN_PARA_KEY_HDI_USERNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HDI_PASSWORD = "CONN_PARA_KEY_HDI_PASSWORD"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_AZURE_HOSTNAME = "CONN_PARA_KEY_AZURE_HOSTNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_AZURE_CONTAINER = "CONN_PARA_KEY_AZURE_CONTAINER"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_AZURE_USERNAME = "CONN_PARA_KEY_AZURE_USERNAME"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_AZURE_PASSWORD = "CONN_PARA_KEY_AZURE_PASSWORD"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_AZURE_DEPLOY_BLOB = "CONN_PARA_KEY_AZURE_DEPLOY_BLOB"; //$NON-NLS-1$

    /******************************************/

    /**
     * HBase keys.
     */
    public static final String CONN_PARA_KEY_HBASE_DISTRIBUTION = "CONN_PARA_KEY_HBASE_DISTRIBUTION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HBASE_VERSION = "CONN_PARA_KEY_HBASE_VERSION"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HBASE_PROPERTIES = "CONN_PARA_KEY_HBASE_PROPERTIES"; //$NON-NLS-1$

    public static final String CONN_PARA_KEY_HBASE_AUTHENTICATION_MASTERPRINCIPAL = "CONN_PARA_KEY_HBASE_AUTHENTICATION_MASTERPRINCIPAL";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_HBASE_AUTHENTICATION_REGIONSERVERPRINCIPAL = "CONN_PARA_KEY_HBASE_AUTHENTICATION_REGIONSERVERPRINCIPAL";//$NON-NLS-1$

    /**
     * Impala keys.
     */
    public static final String CONN_PARA_KEY_IMPALA_DISTRIBUTION = "CONN_PARA_KEY_IMPALA_DISTRIBUTION";//$NON-NLS-1$

    public static final String CONN_PARA_KEY_IMPALA_VERSION = "CONN_PARA_KEY_IMPALA_VERSION"; //$NON-NLS-1$

    public static final String IMPALA_AUTHENTICATION_PRINCIPLA = "IMPALA_AUTHENTICATION_PRINCIPLA";//$NON-NLS-1$
}

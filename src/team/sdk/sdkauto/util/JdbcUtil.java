package team.sdk.sdkauto.util;
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Properties;  

/**
 * <p>Title: JdbcUtil<��p>
 * <p>Description: JDBC������<��p>
 * @author quzhe
 *
 * 2017��11��15��
 */

  
public class JdbcUtil {  
  
    // ��ʾ�������ݿ���û���  
    private static String USERNAME ;  
  
    // �������ݿ������  
    private static String PASSWORD;  
  
    // �������ݿ��������Ϣ  
    private static String DRIVER;  
  
    // ����������ݿ�ĵ�ַ  
    private static String URL;  
  
    // �������ݿ������  
    private  Connection connection;  
  
    // ����sql����ִ�ж���  
    private  PreparedStatement pstmt;  
  
    // �����ѯ���صĽ������  
    private  ResultSet resultSet;  
      
    static{  
        //�������ݿ�������Ϣ��������ص����Ը�ֵ  
        loadConfig();  
    }  
  
    /** 
     * �������ݿ�������Ϣ��������ص����Ը�ֵ 
     */  
    public static void loadConfig() {
        try {
        	//���ݿ������ļ�
        	InputStream inStream = JdbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
        	
            Properties prop = new Properties();  
            prop.load(inStream);  
            USERNAME = prop.getProperty("jdbc.username");  
            PASSWORD = prop.getProperty("jdbc.password");  
            DRIVER= prop.getProperty("jdbc.driver");  
            URL = prop.getProperty("jdbc.url");
            
        }
        catch (Exception e) {  
            throw new RuntimeException("��ȡ���ݿ������ļ��쳣��", e);  
        }  
    }  
  
    public JdbcUtil() {  
  
    }  
  
    /** 
     * ��ȡ���ݿ����� 
     *  
     * @return ���ݿ����� 
     */  
    public Connection getConnection() {
        try {  
            Class.forName(DRIVER).newInstance(); // ע������  
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // ��ȡ����  
        } catch (Exception e) {  
            throw new RuntimeException("get connection error!", e);  
        }
        return connection;
    }

    /**
     * ִ�и��²���
     
     * @param sql
     *            sql���
     * @param params
     *            ִ�в���
     * @return ִ�н��
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {
        boolean flag = false;
        int result = -1;// ��ʾ���û�ִ�����ɾ�����޸ĵ�ʱ����Ӱ�����ݿ������
        pstmt = connection.prepareStatement(sql);
        int index = 1;  
        // ���sql����е�ռλ��  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }
        }
        result = pstmt.executeUpdate();  
        flag = result > 0 ? true : false;  
        return flag;  
    }  
  
    /** 
     * ִ�в�ѯ���� 
     *  
     * @param sql 
     *            sql��� 
     * @param params 
     *            ִ�в��� 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findResult(String sql, List<?> params)  
            throws SQLException {  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        pstmt = connection.prepareStatement(sql);  
        if (params != null && !params.isEmpty()) {  
            for (int i = 0; i < params.size(); i++) {  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        resultSet = pstmt.executeQuery(); 
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  //��ȡ����
        while (resultSet.next()) {  
            Map<String, Object> map = new HashMap<String, Object>();  
            for (int i = 0; i < cols_len; i++) {  
                String cols_name = metaData.getColumnName(i + 1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if (cols_value == null) {  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
        return list;  
    }  
  
    /** 
     * �ͷ���Դ 
     */  
    public void releaseConn() {  
        if (resultSet != null) {
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }
        if (pstmt != null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}  
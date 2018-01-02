# DeviceManager
海康威视测试部SDK测试组设备管理器  

软件简介
	DeviceManager实现了基于MySql数据库管理SDK测试组测试间设备的目的。支持设备的添加、删除、修改、查找功能
设备属性：设备ID、类型、型号、IP、端口、用户名、密码、语言、在线状态、错误码、位置、备注
	ID：随机生成5位数ID，为设备唯一标识。
	类型：设备所属类型，包括：PC、服务器、DVR/NVR、DVS、IPC、IP球、NVR、UTC、半球PTZ、报警主机、车站-DVR、动环监控报警主机、多屏控制器、合码器、会议视频终端、交通IPC、解码器、矩阵、门禁一体机、门禁主机、枪机IPC、热成像、双目IPC、网络视音频解码器、无线门禁主机、信息发布主机、鹰眼、萤石IPC、鱼眼、自助银行报警主机、综合平台、坐式IP球、其他
	端口号：默认8000
	用户名：默认admin
	ErrorCode：默认0
	备注：设备其他信息
主界面有三个功能：添加设备、查找设备、显示全部设备


添加设备：在相应位置添加好设备信息后点击确定。
 
添加成功弹出消息框
 
根据类型查找：可以分别通过设备类型、型号和IP和备注进行设备查找
 
*设备型号、备注可进行模糊搜索，在备注中搜索稳定性或设备信息关键字即可找到相应设备

查找后弹出界面，可进行对设备信息的修改和删除操作
设备状态显示绿色为在线设备 红色为不在线

双击列表中设备信息进行修改编辑后点击“修改”按钮进行对设备信息的修改
删除设备信息点击“删除”按钮，弹出提示框，确认后删除。

v1.2新增：
1.	筛选设备：可通过选择“在线”或“不在线”后点击“筛选”，筛选出在线或不在线设备。
2.	导出Excel：导出当前列表生成Excel文件，路径为\ Generate Files\Excel\

显示全部设备：列出数据库中所有的设备信息，也可以进行修改和删除操作


Zip包中包含文件：
	jre：java运行环境
	lib：JDBC连接mysql所需要的依赖库、DeviceManager.jar（如果PC上有JRE可直接运行该文件）
	.exe：可执行文件
	说明文档
 

工程简介
 
DeviceManager工程主要由5个类构成：
	Device：设备属性，并实现了设备添加功能
	DeviceInsert: 设备添加的UI界面
	DeviceList：设备查找、显示全部设备的设备列表，并实现了设备查找、修改、删除功能
	DeviceSelect：设备查找UI界面
	DeviceUI:DeviceManagerUI界面
	
JDBC为JDBC（Java DataBase Connectivity,java数据库连接）是一种用于执行SQL语句的Java API。JdbcUtil.java为JDBC工具类，使用该类方法进行对数据库的增删改查。
jdbc.properties：数据库信息，可以通过修改该文件选择使用的数据库。

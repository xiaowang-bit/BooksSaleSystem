
#include<iostream>
#include<cstdio>
#include<cstdlib>
#include<string>
#include<cstring>
#include <sys/time.h>
#include<stack>
#pragma comment(linker, "/STACK:102400000,102400000") 		
using namespace std;
 
//文件结构体
struct file{
	string f_text;//存放文本
	unsigned int f_size;//文件大小
	string create_data;//文件创建日期
	string update_data;//文件最近修改日期
	string visit_data;//文件最近访问日期
};
//I结点结构体
struct inode{ 
	struct inode *i_prev;
	struct inode *i_next;
	struct inode *i_extend;//延伸结点
	char i_type;//文件类型
	unsigned short i_flag;//文件访问,1可访问
	unsigned int i_mode;//文件权限
	unsigned short i_uid;//用户ID
	string i_name;//文件名字
	string create_data;//文件创建日期
	string update_data;//文件最近修改日期
	string visit_data;//文件最近访问日期

	unsigned int i_size;//目录文件大小
	struct file *f;//文件指针
};
//用户结构体
struct user{
	unsigned int uid;//用户ID
	string username;//用户名
	string password;//用户密码
};
//创建用户结构体
struct crt_user{
	struct user *currentP;//当前用户指针
	struct crt_user *nextP;//下一用户指针
	struct crt_user *prevP;//上一用户指针
};
 
//文件系统类
class Fileos{
	public:
		void cd();
		void cat();
		void chmod();
		bool judgeMode(unsigned int mode);
		void InitNode();
		void createSuper();
		void help();
		void ll();
		void outPermission(unsigned int mode);
		void login();
		void logout();
		void mkdir(string dirname,inode *preNode,unsigned int  mode,unsigned short i_uid);
		void userMkdir();
		void pwd();
		void touch();
		void vim();
		void rm();
		void rmdir();
		string getTime();
};
crt_user currentUser;//当前用户
crt_user *rootUser;//指向第一个用户的指针
user *up;//创建用户指针
 
inode *node;//当前节点
inode *rootNode;//根目录结点
inode *nextNode;//下一结点
inode *lastNode;//上一结点
inode *currentRootNode;//当前目录结点
 
 
string CURRENTUSER;//当前用户
unsigned int CURRENTUSERUID;//当前用户ID
string CURRENTDIR;//当前路径
 
char userTable[10][25]; //记录用户的表
unsigned int userCount = 0;//记录用户的个数
 
file *fp;
Fileos op;

stack<inode *> i_stack;
void pause()
{
	cout<<"请按任意键继续..."<<endl;
	fgetc(stdin);
}
string Fileos::getTime()
{
	time_t t = time(NULL);
	char ch[64] = {0};
	strftime(ch, sizeof(ch) - 1, "%Y-%m-%d %H:%M:%S", localtime(&t));
	string localTime = ch;
	return localTime;
}
void Fileos::help()
{
	cout<<"        ***************************************************************"<<endl;
	cout<<"        *         欢迎使用文件系统.本系统中你可以使用如下的命令：     *"<<endl;
	cout<<"        *               命令                说明                      *"<<endl;
	cout<<"        *                                                             *"<<endl;
	cout<<"        *               cd                  进入文件夹                *"<<endl;
	cout<<"        *               read                读取文件的内容            *"<<endl;
	cout<<"        *               chmod               修改文件的权限            *"<<endl;
	cout<<"        *               help                查看文件系统的命令        *"<<endl;
	cout<<"        *               dir                 列文件目录                *"<<endl;
	cout<<"        *               logout              注销                      *"<<endl;
	cout<<"        *               mkdir               创建文件目录              *"<<endl;
	cout<<"        *               delete              删除文件                  *"<<endl;
	cout<<"        *               deletedir           删除文件目录              *"<<endl;
	cout<<"        *               create              创建文件                  *"<<endl;
	cout<<"        *               write               向文件中写入内容          *"<<endl;
	cout<<"        ***************************************************************"<<endl;
	cout<<"        注：所有命令都是单独的命令，输入文件名回车后再进行下一步的输入。"<<endl;
}
void Fileos::createSuper()
{
	string password1,password2;
	string name;
	up = new user;
	currentUser.nextP = NULL;
	currentUser.prevP = NULL;
	up->uid = 0;
	cout<<"**************************************************************************************************************"<<endl;
	cout<<"                                              欢迎进入文件系统                    "<<endl;
	cout<<"                                    您是第一次使用本系统，因此请您先创建超级权限用户. "<<endl;
	cout<<"                                    超级用户:拥有系统的所有权限!请谨慎使用和保管!     "<<endl;
	cout<<"***************************************************************************************************************"<<endl;
	cout<<"请出入您要创建的用户名:";
	cin>>name;
	up->username = name;
	cout<<"**********************************"<<endl;
	while(true)
	{
		cout<<"请输入密码:";
		cin>>password1;
		cout<<"请确认密码:";
		cin>>password2;
		if(password1 == password2) break;
		else
			cout<<"两次输入的密码不一致!请重新输入."<<endl;
	}
	getchar();//防止和fgetc碰撞，用getchar来读入回车
	up->password = password1;
	currentUser.currentP = up;
	strcpy(userTable[0],name.c_str());
	userCount++;
	cout<<endl;
	cout<<"**********************************"<<endl;
	cout<<"用户"<<name<<"(root)"<<"创建成功！即将进入文件系统..."<<endl;
	pause();
}
void Fileos::login()
{
	int times = 0;
	string username;
	string password;
	crt_user *nowLogin;
	crt_user *nextLogin;
	nowLogin = &currentUser;
	nextLogin = currentUser.nextP;
	cout<<"**********************************"<<endl;

	cout<<"请输入用户名密码进行登录:"<<endl;
	cout<<"Username:";
	cin>>username;
	while(nowLogin!=NULL)
	{
		if(nowLogin->currentP->username == username)
			break;
		else
		{
			nowLogin = nextLogin;
			if(nextLogin!=NULL)
				nextLogin = nextLogin->nextP;
		}
	}
	if(nowLogin == NULL)
	{
		cout<<"不存在该用户,请输入正确的用户名."<<endl;
		exit(-1);
	}
	while(times<3)
	{
		cout<<"Password:";
		cin>>password;
		if(password == nowLogin->currentP->password)
			break;
		times++;
		cout<<endl;
		cout<<"输错密码"<<times<<"次."<<"输错三次密码将自动退出程序!"<<endl;
	}
	if(times == 3)
		exit(-2);
	cout<<endl;
	cout<<"用户登录成功!欢迎"<<username<<"！！！！！"<<endl;
	CURRENTUSER = username;	

}
void Fileos::mkdir(string dirname,inode *preNode,unsigned int mode,unsigned short uid)
{
	nextNode = new inode;
	preNode->i_next = nextNode;
	nextNode->i_prev = preNode;
	nextNode->i_next = NULL;
	nextNode->i_extend = NULL;
	nextNode->i_mode = mode;
	nextNode->i_flag = 1;
	nextNode->i_size = 4*1024;//一个目录占一个block,4KB
	nextNode->i_type = 'd';
	nextNode->i_uid = uid;
	nextNode->i_name = dirname;
	nextNode->create_data = op.getTime();
}
void Fileos::userMkdir()
{
	inode *p;
	string dirname;
	p = currentRootNode;
	cout<<"请输入要创建的目录名:";
	cin>>dirname;
	while(p->i_next!=NULL && p->i_name!=dirname)
		p = p->i_next;
	if(p->i_next == NULL && p->i_name!=dirname)
	{
		mkdir(dirname,p,755,CURRENTUSERUID);
		p->i_next = nextNode;
		cout<<"目录创建成功!"<<endl;
	}
	else
		cout<<"mkdir: "<<"无法创建目录"<<"\""<<dirname<<"\""<<": 文件已存在"<<endl;
}
void Fileos::logout()
{
	cout<<"注销成功! "<<"Bye, "<<CURRENTUSER<<"."<<endl;
	exit(0);
}
void Fileos::pwd()
{
	cout<<CURRENTDIR<<endl;
}
void Fileos::ll()
{
	inode *p;
	p = currentRootNode->i_next;
	while(p!=NULL)
	{
		unsigned int user = p->i_mode/100;
		unsigned int group = p->i_mode/10%10;
		unsigned int others = p->i_mode%10;
		cout<<"************************"<<endl;

		if(p->i_type == 'd' && (p->i_flag == 1 || p->i_flag == 0)){ 
			cout<<"     读取权限："<<user;
			cout<<group;
			cout<<others<<endl;
			cout<<"       文件名："<<p->i_name.c_str()<<endl;
			cout<<"     文件大小："<<p->i_size<<endl;
			cout<<"     创建时间："<<p->create_data.c_str()<<endl;
			cout<<" 最近修改时间："<<p->update_data.c_str()<<endl;
			cout<<" 最近访问时间："<<p->visit_data.c_str()<<endl;
		} 
			
		else if(p->i_type != 'd' && (p->i_flag == 1 || p->i_flag == 0)){ 
			cout<<"     读取权限："<<user;
			cout<<group;
			cout<<others<<endl;
			cout<<"       文件名："<<p->i_name.c_str()<<endl;
			cout<<"     文件大小："<<p->i_size<<endl;
			cout<<"     创建时间："<<p->f->create_data.c_str()<<endl;
			cout<<" 最近修改时间："<<p->f->update_data.c_str()<<endl;
			cout<<" 最近访问时间："<<p->f->visit_data.c_str()<<endl;
			} 
		p = p->i_next;
	}
}
bool Fileos::judgeMode(unsigned int mode)
{	
	unsigned int u,g,o;
	u = mode/100;
	g = mode/10%10;
	o = mode%10;
	if(u>=0 && u<=7 && g>=0 && g<=7 && o>=0 && o<=7)
		return true;
	else return false;
}
void Fileos::chmod()
{
	inode *p;
	string filename;
	unsigned int mode;
	cout<<"请输入要修改的文件的名字:";
	cin>>filename;
	p = currentRootNode;
	cout<<"请输入要修改的mode:";
	cin>>mode;
	if(judgeMode(mode))
	{
		while(p!=NULL)
		{
			if((p->i_name == filename && p->i_flag == 1) && (p->i_type == 'd' || p->i_type != 'd'))
			{
				if(CURRENTUSERUID!=0 && CURRENTUSERUID != p->i_uid)
				{
					cout<<"你的用户没有chmod权限!"<<endl;
					break;
				}
				p->i_mode = mode;
				p->create_data = op.getTime();
				cout<<"修改权限成功!"<<endl;
				break;
			}
			else
				p = p->i_next;
		}
		if(p == NULL)
			cout<<"chmod: 无法访问"<<"\'"<<filename<<"\'"<<": 没有这个文件或目录"<<endl;
	}
	else
		cout<<"错误的mode!"<<endl;
}
void Fileos::touch()
{
	string filename;
	inode *p;
	file *newFile;
	p = currentRootNode;
	cout<<"请输入要创建的文件的名字:";
	cin>>filename;
	while(p->i_next!=NULL && p->i_name != filename)
		p = p->i_next;
	if(p->i_next == NULL && p->i_name != filename)
	{
		nextNode = new inode;
		newFile = new file;
		lastNode = p;
		lastNode->i_next = nextNode;
		nextNode->i_prev = lastNode;
		nextNode->i_next = NULL;
		nextNode->i_extend = NULL;
		nextNode->i_uid = CURRENTUSERUID;
		cout<<"请输入要创建的文件的权限:";
		int i_mode; 
		cin>>i_mode;
		nextNode->i_mode = i_mode;
		nextNode->i_flag = 1;
		nextNode->i_type = '-';
		newFile->f_size = 0;
		newFile->create_data=op.getTime();
		newFile->create_data = op.getTime();
		nextNode->f = newFile;
		nextNode->i_name = filename;
		lastNode = nextNode;
		cout<<"创建文件成功!"<<endl;
	}
	else
		cout<<"touch: "<<"无法创建新文件"<<"\""<<filename<<"\""<<": 文件已存在"<<endl;
}
void Fileos::vim()
{
	string filename;
	string text;
	inode *p = currentRootNode;
	cout<<"请输入要编辑的文件的名字:";
	cin>>filename;
	getchar();
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"错误: 目录不可写!"<<endl;
		else if(p->i_type != 'd')
		{
			if(CURRENTUSERUID!=0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&2) == 0)
				cout<<"你的用户没有写入文件内容的权限!"<<endl;
			else
			{
				cout<<"请输入文件的内容:";
				getline(cin,text);
				p->f->f_text = text;
				p->f->f_size = text.length() * sizeof(char);
				p->i_size = text.length() * sizeof(char);

				p->f->update_data = op.getTime();
				cout<<"编辑文件成功!"<<endl;
			}
		}
	}
	else
		cout<<"vim: 无法编辑"<<"\'"<<filename<<"\'"<<": 没有这个文件"<<endl;
}
void Fileos::cat()
{
	string filename;
	inode *p = currentRootNode;
	cout<<"请输入要查看的文件的名字:";
	cin>>filename;
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"错误: 目录不可读!"<<endl;
		else if(p->i_type != 'd')
		{
			if(CURRENTUSERUID !=0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&4) == 0)
				cout<<"你的用户没有读取文件内容的权限!"<<endl;
			else if(p->f->f_size == 0)
				cout<<endl;
			else
				cout<<p->f->f_text<<endl;
			p->f->visit_data=op.getTime();	
			
		}
	}
	else
		cout<<"cat: 无法查看"<<"\'"<<filename<<"\'"<<": 没有这个文件"<<endl;
}
void Fileos::rm()
{
	string filename;
	inode *p = currentRootNode;
	inode *Prev = new inode;
	cout<<"请输入要删除的文件的名字:";
	cin>>filename;
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"错误: rm命令不可对目录使用! 删除目录 ---- rmdir"<<endl;
		else if(p->i_type !='d')
		{
			if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid)
				cout<<"你的用户没有删除文件的权限!"<<endl;
			else
			{
				Prev = p->i_prev;
				Prev->i_next = p->i_next;
				if(Prev->i_next != NULL)
					p->i_next->i_prev = p->i_prev;
				delete p;
				cout<<"文件删除成功!"<<endl;
			}
		}
	}
	else	
		cout<<"rm: 无法删除"<<"\'"<<filename<<"\'"<<": 没有这个文件"<<endl;
}
void Fileos::rmdir()
{
	string dirname;
	inode *p = currentRootNode;
	inode *Prev = new inode;
	cout<<"请输入要删除的目录的名字:";
	cin>>dirname;
	while(p->i_name != dirname && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == dirname)
	{
		if(p->i_type != 'd')
			cout<<"错误: rmdir命令不可对文件使用! 删除文件 ---- rm"<<endl;
		else if(p->i_type == 'd')
		{
			if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid)
				cout<<"你的用户没有删除目录的权限!"<<endl;
			else
			{
				Prev = p->i_prev;
				Prev->i_next = p->i_next;
				if(Prev->i_next != NULL)
					p->i_next->i_prev = p->i_prev;
				delete p;
				cout<<"目录删除成功!"<<endl;
			}
		}
	}
	else
		cout<<"rmdir: 无法删除"<<"\'"<<dirname<<"\'"<<": 没有这个目录"<<endl;
}
void Fileos::cd()
{
	string dirname;
	inode *p;
	inode *newNode;
	p = currentRootNode;
	cout<<"请输入要访问的目录的名字:";
	cin>>dirname;
	while(p->i_next!=NULL && p->i_name != dirname)
		p = p->i_next;
	if(p!=NULL && p->i_name == dirname)
	{
		if(p->i_type != 'd')
			cout<<"错误: 文件无法使用cd命令访问!"<<endl;
		else if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&1) == 0)
			cout<<"你的用户没有访问目录的权限!"<<endl;
		else if(p->i_extend == NULL)
		{
			newNode = new inode;
			CURRENTDIR = CURRENTDIR+dirname;
			CURRENTDIR = CURRENTDIR+"/";
			newNode->i_prev = p;
			newNode->i_next = NULL;
			p->i_extend = newNode;
			i_stack.push(currentRootNode);
			currentRootNode = newNode;
		}
		else
		{
			CURRENTDIR = CURRENTDIR+dirname;
			CURRENTDIR = CURRENTDIR+"/";
			i_stack.push(currentRootNode);
			currentRootNode = p->i_extend;
		}
	}
	else if(p!=NULL && dirname  == "..")
	{
		if(i_stack.empty())
			cout<<"错误: 无法返回上一级目录! 当前目录已是根目录."<<endl;
		else
		{
			currentRootNode = i_stack.top();
			i_stack.pop();
			int len = CURRENTDIR.length();
			char tmp[105];
			strcpy(tmp,CURRENTDIR.c_str());
			for(int i = len-2;i>=0;i--)
			{
				if(tmp[i] == '/')
				{
					tmp[i+1] = '\0';
					CURRENTDIR = tmp;
					break;
				}
			}
		}
	}
	else
		cout<<"cd: 无法访问"<<"\'"<<dirname<<"\'"<<": 没有这个目录"<<endl;
}
void Fileos::InitNode()
{
	while(!i_stack.empty())
		i_stack.pop();
	node = new inode;
	node->i_prev = NULL;
	node->i_next = NULL;
	node->i_uid = 0;
	node->i_mode = 755;
	node->i_flag  = 1;
	node->i_type = 'd';
	node->i_name = "/";
	CURRENTDIR = node->i_name;
	rootNode = node;
	op.mkdir("usr",node,755,0);
	node = nextNode;
	lastNode = node;
	node = rootNode;
	currentRootNode = rootNode;
	rootUser = &currentUser;
}
void File_os()
{
	string command;
	op.createSuper();
	op.login();
	op.help();
	op.InitNode();
	while(true)
	{
		if(CURRENTUSER == rootUser->currentP->username)
			cout<<CURRENTUSER<<"(root)@wangaoxing:"<<CURRENTDIR<<"$ ";
		else
			cout<<CURRENTUSER<<"@wangaoxing:"<<CURRENTDIR<<"$ ";
		cin>>command;
		if(command == "help")
			op.help();
		else if(command == "logout")
		{
			op.logout();
			break;
		}
		else if(command == "pwd")
			op.pwd();
		else if(command == "mkdir")
			op.userMkdir();
		else if(command == "dir")
			op.ll();
		else if(command == "chmod")
			op.chmod();
		else if(command == "create")
			op.touch();
		else if(command == "write")
			op.vim();
		else if(command == "read")
			op.cat();
		else if(command == "delete")
			op.rm();
		else if(command == "deletedir")
			op.rmdir();
		else if(command == "cd")
			op.cd();
		else
		{
			cout<<"未找到"<<" '"<<command<<"' "<<"命令"<<endl;
			cout<<command<<": "<<"未找到命令"<<endl;
		}
	}
}
int main()
{
	File_os();
	return 0;
}


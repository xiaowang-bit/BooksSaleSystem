
#include<iostream>
#include<cstdio>
#include<cstdlib>
#include<string>
#include<cstring>
#include <sys/time.h>
#include<stack>
#pragma comment(linker, "/STACK:102400000,102400000") 		
using namespace std;
 
//�ļ��ṹ��
struct file{
	string f_text;//����ı�
	unsigned int f_size;//�ļ���С
	string create_data;//�ļ���������
	string update_data;//�ļ�����޸�����
	string visit_data;//�ļ������������
};
//I���ṹ��
struct inode{ 
	struct inode *i_prev;
	struct inode *i_next;
	struct inode *i_extend;//������
	char i_type;//�ļ�����
	unsigned short i_flag;//�ļ�����,1�ɷ���
	unsigned int i_mode;//�ļ�Ȩ��
	unsigned short i_uid;//�û�ID
	string i_name;//�ļ�����
	string create_data;//�ļ���������
	string update_data;//�ļ�����޸�����
	string visit_data;//�ļ������������

	unsigned int i_size;//Ŀ¼�ļ���С
	struct file *f;//�ļ�ָ��
};
//�û��ṹ��
struct user{
	unsigned int uid;//�û�ID
	string username;//�û���
	string password;//�û�����
};
//�����û��ṹ��
struct crt_user{
	struct user *currentP;//��ǰ�û�ָ��
	struct crt_user *nextP;//��һ�û�ָ��
	struct crt_user *prevP;//��һ�û�ָ��
};
 
//�ļ�ϵͳ��
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
crt_user currentUser;//��ǰ�û�
crt_user *rootUser;//ָ���һ���û���ָ��
user *up;//�����û�ָ��
 
inode *node;//��ǰ�ڵ�
inode *rootNode;//��Ŀ¼���
inode *nextNode;//��һ���
inode *lastNode;//��һ���
inode *currentRootNode;//��ǰĿ¼���
 
 
string CURRENTUSER;//��ǰ�û�
unsigned int CURRENTUSERUID;//��ǰ�û�ID
string CURRENTDIR;//��ǰ·��
 
char userTable[10][25]; //��¼�û��ı�
unsigned int userCount = 0;//��¼�û��ĸ���
 
file *fp;
Fileos op;

stack<inode *> i_stack;
void pause()
{
	cout<<"�밴���������..."<<endl;
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
	cout<<"        *         ��ӭʹ���ļ�ϵͳ.��ϵͳ�������ʹ�����µ����     *"<<endl;
	cout<<"        *               ����                ˵��                      *"<<endl;
	cout<<"        *                                                             *"<<endl;
	cout<<"        *               cd                  �����ļ���                *"<<endl;
	cout<<"        *               read                ��ȡ�ļ�������            *"<<endl;
	cout<<"        *               chmod               �޸��ļ���Ȩ��            *"<<endl;
	cout<<"        *               help                �鿴�ļ�ϵͳ������        *"<<endl;
	cout<<"        *               dir                 ���ļ�Ŀ¼                *"<<endl;
	cout<<"        *               logout              ע��                      *"<<endl;
	cout<<"        *               mkdir               �����ļ�Ŀ¼              *"<<endl;
	cout<<"        *               delete              ɾ���ļ�                  *"<<endl;
	cout<<"        *               deletedir           ɾ���ļ�Ŀ¼              *"<<endl;
	cout<<"        *               create              �����ļ�                  *"<<endl;
	cout<<"        *               write               ���ļ���д������          *"<<endl;
	cout<<"        ***************************************************************"<<endl;
	cout<<"        ע����������ǵ�������������ļ����س����ٽ�����һ�������롣"<<endl;
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
	cout<<"                                              ��ӭ�����ļ�ϵͳ                    "<<endl;
	cout<<"                                    ���ǵ�һ��ʹ�ñ�ϵͳ����������ȴ�������Ȩ���û�. "<<endl;
	cout<<"                                    �����û�:ӵ��ϵͳ������Ȩ��!�����ʹ�úͱ���!     "<<endl;
	cout<<"***************************************************************************************************************"<<endl;
	cout<<"�������Ҫ�������û���:";
	cin>>name;
	up->username = name;
	cout<<"**********************************"<<endl;
	while(true)
	{
		cout<<"����������:";
		cin>>password1;
		cout<<"��ȷ������:";
		cin>>password2;
		if(password1 == password2) break;
		else
			cout<<"������������벻һ��!����������."<<endl;
	}
	getchar();//��ֹ��fgetc��ײ����getchar������س�
	up->password = password1;
	currentUser.currentP = up;
	strcpy(userTable[0],name.c_str());
	userCount++;
	cout<<endl;
	cout<<"**********************************"<<endl;
	cout<<"�û�"<<name<<"(root)"<<"�����ɹ������������ļ�ϵͳ..."<<endl;
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

	cout<<"�������û���������е�¼:"<<endl;
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
		cout<<"�����ڸ��û�,��������ȷ���û���."<<endl;
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
		cout<<"�������"<<times<<"��."<<"����������뽫�Զ��˳�����!"<<endl;
	}
	if(times == 3)
		exit(-2);
	cout<<endl;
	cout<<"�û���¼�ɹ�!��ӭ"<<username<<"����������"<<endl;
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
	nextNode->i_size = 4*1024;//һ��Ŀ¼ռһ��block,4KB
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
	cout<<"������Ҫ������Ŀ¼��:";
	cin>>dirname;
	while(p->i_next!=NULL && p->i_name!=dirname)
		p = p->i_next;
	if(p->i_next == NULL && p->i_name!=dirname)
	{
		mkdir(dirname,p,755,CURRENTUSERUID);
		p->i_next = nextNode;
		cout<<"Ŀ¼�����ɹ�!"<<endl;
	}
	else
		cout<<"mkdir: "<<"�޷�����Ŀ¼"<<"\""<<dirname<<"\""<<": �ļ��Ѵ���"<<endl;
}
void Fileos::logout()
{
	cout<<"ע���ɹ�! "<<"Bye, "<<CURRENTUSER<<"."<<endl;
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
			cout<<"     ��ȡȨ�ޣ�"<<user;
			cout<<group;
			cout<<others<<endl;
			cout<<"       �ļ�����"<<p->i_name.c_str()<<endl;
			cout<<"     �ļ���С��"<<p->i_size<<endl;
			cout<<"     ����ʱ�䣺"<<p->create_data.c_str()<<endl;
			cout<<" ����޸�ʱ�䣺"<<p->update_data.c_str()<<endl;
			cout<<" �������ʱ�䣺"<<p->visit_data.c_str()<<endl;
		} 
			
		else if(p->i_type != 'd' && (p->i_flag == 1 || p->i_flag == 0)){ 
			cout<<"     ��ȡȨ�ޣ�"<<user;
			cout<<group;
			cout<<others<<endl;
			cout<<"       �ļ�����"<<p->i_name.c_str()<<endl;
			cout<<"     �ļ���С��"<<p->i_size<<endl;
			cout<<"     ����ʱ�䣺"<<p->f->create_data.c_str()<<endl;
			cout<<" ����޸�ʱ�䣺"<<p->f->update_data.c_str()<<endl;
			cout<<" �������ʱ�䣺"<<p->f->visit_data.c_str()<<endl;
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
	cout<<"������Ҫ�޸ĵ��ļ�������:";
	cin>>filename;
	p = currentRootNode;
	cout<<"������Ҫ�޸ĵ�mode:";
	cin>>mode;
	if(judgeMode(mode))
	{
		while(p!=NULL)
		{
			if((p->i_name == filename && p->i_flag == 1) && (p->i_type == 'd' || p->i_type != 'd'))
			{
				if(CURRENTUSERUID!=0 && CURRENTUSERUID != p->i_uid)
				{
					cout<<"����û�û��chmodȨ��!"<<endl;
					break;
				}
				p->i_mode = mode;
				p->create_data = op.getTime();
				cout<<"�޸�Ȩ�޳ɹ�!"<<endl;
				break;
			}
			else
				p = p->i_next;
		}
		if(p == NULL)
			cout<<"chmod: �޷�����"<<"\'"<<filename<<"\'"<<": û������ļ���Ŀ¼"<<endl;
	}
	else
		cout<<"�����mode!"<<endl;
}
void Fileos::touch()
{
	string filename;
	inode *p;
	file *newFile;
	p = currentRootNode;
	cout<<"������Ҫ�������ļ�������:";
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
		cout<<"������Ҫ�������ļ���Ȩ��:";
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
		cout<<"�����ļ��ɹ�!"<<endl;
	}
	else
		cout<<"touch: "<<"�޷��������ļ�"<<"\""<<filename<<"\""<<": �ļ��Ѵ���"<<endl;
}
void Fileos::vim()
{
	string filename;
	string text;
	inode *p = currentRootNode;
	cout<<"������Ҫ�༭���ļ�������:";
	cin>>filename;
	getchar();
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"����: Ŀ¼����д!"<<endl;
		else if(p->i_type != 'd')
		{
			if(CURRENTUSERUID!=0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&2) == 0)
				cout<<"����û�û��д���ļ����ݵ�Ȩ��!"<<endl;
			else
			{
				cout<<"�������ļ�������:";
				getline(cin,text);
				p->f->f_text = text;
				p->f->f_size = text.length() * sizeof(char);
				p->i_size = text.length() * sizeof(char);

				p->f->update_data = op.getTime();
				cout<<"�༭�ļ��ɹ�!"<<endl;
			}
		}
	}
	else
		cout<<"vim: �޷��༭"<<"\'"<<filename<<"\'"<<": û������ļ�"<<endl;
}
void Fileos::cat()
{
	string filename;
	inode *p = currentRootNode;
	cout<<"������Ҫ�鿴���ļ�������:";
	cin>>filename;
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"����: Ŀ¼���ɶ�!"<<endl;
		else if(p->i_type != 'd')
		{
			if(CURRENTUSERUID !=0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&4) == 0)
				cout<<"����û�û�ж�ȡ�ļ����ݵ�Ȩ��!"<<endl;
			else if(p->f->f_size == 0)
				cout<<endl;
			else
				cout<<p->f->f_text<<endl;
			p->f->visit_data=op.getTime();	
			
		}
	}
	else
		cout<<"cat: �޷��鿴"<<"\'"<<filename<<"\'"<<": û������ļ�"<<endl;
}
void Fileos::rm()
{
	string filename;
	inode *p = currentRootNode;
	inode *Prev = new inode;
	cout<<"������Ҫɾ�����ļ�������:";
	cin>>filename;
	while(p->i_name!=filename && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == filename)
	{
		if(p->i_type == 'd')
			cout<<"����: rm����ɶ�Ŀ¼ʹ��! ɾ��Ŀ¼ ---- rmdir"<<endl;
		else if(p->i_type !='d')
		{
			if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid)
				cout<<"����û�û��ɾ���ļ���Ȩ��!"<<endl;
			else
			{
				Prev = p->i_prev;
				Prev->i_next = p->i_next;
				if(Prev->i_next != NULL)
					p->i_next->i_prev = p->i_prev;
				delete p;
				cout<<"�ļ�ɾ���ɹ�!"<<endl;
			}
		}
	}
	else	
		cout<<"rm: �޷�ɾ��"<<"\'"<<filename<<"\'"<<": û������ļ�"<<endl;
}
void Fileos::rmdir()
{
	string dirname;
	inode *p = currentRootNode;
	inode *Prev = new inode;
	cout<<"������Ҫɾ����Ŀ¼������:";
	cin>>dirname;
	while(p->i_name != dirname && p->i_next!=NULL)
		p = p->i_next;
	if(p!=NULL && p->i_name == dirname)
	{
		if(p->i_type != 'd')
			cout<<"����: rmdir����ɶ��ļ�ʹ��! ɾ���ļ� ---- rm"<<endl;
		else if(p->i_type == 'd')
		{
			if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid)
				cout<<"����û�û��ɾ��Ŀ¼��Ȩ��!"<<endl;
			else
			{
				Prev = p->i_prev;
				Prev->i_next = p->i_next;
				if(Prev->i_next != NULL)
					p->i_next->i_prev = p->i_prev;
				delete p;
				cout<<"Ŀ¼ɾ���ɹ�!"<<endl;
			}
		}
	}
	else
		cout<<"rmdir: �޷�ɾ��"<<"\'"<<dirname<<"\'"<<": û�����Ŀ¼"<<endl;
}
void Fileos::cd()
{
	string dirname;
	inode *p;
	inode *newNode;
	p = currentRootNode;
	cout<<"������Ҫ���ʵ�Ŀ¼������:";
	cin>>dirname;
	while(p->i_next!=NULL && p->i_name != dirname)
		p = p->i_next;
	if(p!=NULL && p->i_name == dirname)
	{
		if(p->i_type != 'd')
			cout<<"����: �ļ��޷�ʹ��cd�������!"<<endl;
		else if(CURRENTUSERUID != 0 && CURRENTUSERUID != p->i_uid && ((p->i_mode%10)&1) == 0)
			cout<<"����û�û�з���Ŀ¼��Ȩ��!"<<endl;
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
			cout<<"����: �޷�������һ��Ŀ¼! ��ǰĿ¼���Ǹ�Ŀ¼."<<endl;
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
		cout<<"cd: �޷�����"<<"\'"<<dirname<<"\'"<<": û�����Ŀ¼"<<endl;
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
			cout<<"δ�ҵ�"<<" '"<<command<<"' "<<"����"<<endl;
			cout<<command<<": "<<"δ�ҵ�����"<<endl;
		}
	}
}
int main()
{
	File_os();
	return 0;
}


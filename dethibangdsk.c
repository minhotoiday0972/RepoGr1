#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define max 10
int datham[max];
int truoc[max];
struct dsk{
	int dinhke;
	struct dsk *lk;
};
struct dothi{
	int sodinh;
	struct dsk *ds[max];
};
//them
void them(int i,int j,struct dothi *g){
	struct dsk *p;
	p=(struct dsk*) malloc(sizeof(struct dsk));
	p->dinhke=j;
	p->lk=g->ds[i];
	g->ds[i]=p;
}
//doc tep
void doctep(char *tentep,struct dothi *g){
	FILE *f;int i,j,tg;
	f=fopen(tentep,"r");
	fscanf(f,"%d",&g->sodinh);
	for(i=0;i<g->sodinh;i++) g->ds[i]=NULL;
	for(i=0;i<g->sodinh;i++){
		for(j=0;j<g->sodinh;j++){
			fscanf(f,"%d",&tg);
			if(tg==1){
				them(i,j,g);
			}
		}
	}
	fclose(f);
}
void indothi(struct dothi g){
	struct dsk *p;int i;
	printf("so dinh:%d\n",g.sodinh);
	printf("cac ds ke:\n");
	for(i=0;i<g.sodinh;i++){
		printf("danh sach ke thu %d:",i);
		p=g.ds[i];
		while(p!=NULL){
			printf("%d",p->dinhke);
			p=p->lk;
		}
		printf("\n");
	}
}
//duyet rong
void duyetrong(struct dothi g,int v){
	int q[max],dau,cuoi,w;
	struct dsk *p;
	dau=0;cuoi=0;q[0]=v;datham[v]=1;
	while(dau<=cuoi){
		v=q[dau]; dau++;
		p=g.ds[v];
		while(p!=NULL){
			w=p->dinhke;
			if(datham[w]==0){
				cuoi++;
				q[cuoi]=w;
				truoc[w]=v;
				datham[w]=1;
			}
			p=p->lk;
		}
	}
}
int ddi(struct dothi g,int s,int t){
	int i;
	for(i=0;i<g.sodinh;i++){
		datham[i]=0;
		truoc[i]=-1;
	}
	duyetrong(g,s);
	if(datham[t]==1){
		return 1;
	}else{
		return 0;
    }
}
//in duong di
void indd(int x,int y){
	int z=y;
	while(z!=x){
		printf("%d<--",z);
		z=truoc[z];
	}
	printf("%d\n",x);
}
//sv x muon biet nganh hoc cua sv y thi hoi it nhat bao nhiue ba
void ddnn(struct dothi g,int s,int t){
	int d=0;
	if(ddi(g,s,t)==1){
		int z=t;
		while(z!=s){
			d+=1;
			z=truoc[z];
		}
		if(d==1){
			printf("biet truc tiep");
		}else{
			printf("biet gian tiep thong qua it nhat %d ban\n",d-1);
			int z=truoc[t];
			while(z!=s){
				printf("%d<--",z);
				z=truoc[z];
			}
		}
	}
}
//cho biet sv thu k biet nganhf hoc bao nhieu ban
void bietnganhhoc(struct dothi g,int x){
	int i,s;
	s=0;
	    duyetrong(g,x);
	    for(i=0;i<g.sodinh;i++){
			if(datham[i]==1){
				printf("%d",i);
				s=s+1;
			}else{
				s=s;
			}
		}
		printf("--->%d sv",s-1);
		printf("\n");
}
void main(){
	struct dothi g;
	int x,e,a,b;
	doctep("DT2.txt",&g);
	indothi(g);
	printf("nhap sinh vien can kiem tra:");
	scanf("%d",&x);
	printf("nhung sv ma SV %d biet la:\n",x);
	bietnganhhoc(g,x);
	fflush(stdin);
	printf("nhung sinh vien dau:");scanf("%d",&a);
	printf("nhung sinh vien can biet:");scanf("%d",&b);
	e=ddi(g,a,b);
	if(e==1){
		printf("SV %d biet nganh hoc cua sv %d\n",a,b);
		indd(a,b);
		ddnn(g,a,b);
	}
	else{
		printf("SV %d ko biet nganh hoc cua sv %d\n",a,b);
	}
}
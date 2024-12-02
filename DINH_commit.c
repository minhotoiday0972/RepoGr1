#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define max 10
//danh sach ke
struct Dske{
	int dinhke;
	struct Dske *link;
};
struct dothi{
	int sodinh;
	struct Dske *dske[max];
};
//them dinh j vao danh sach ke dinh i
void them(int i,int j,struct dothi *g){
	struct Dske* p;
	p=(struct Dske*) malloc (sizeof(struct Dske));
	p->dinhke=j;
	p->link=g->dske[i];
	g->dske[i]=p;
}
//doc tep
void doctep(char *tentep,struct dothi *g){
	FILE *f; int i,j,tg;
	f=fopen(tentep,"r");
	fscanf(f,"%d",&g->sodinh);
	for(i=0;i<g->sodinh;i++) g->dske[i]=NULL;
	for(i=0;i<g->sodinh;i++){
		for(j=0;j<g->sodinh;j++){
			fscanf(f,"%d",&tg);
			if(tg==1) them(i,j,g);
		}
	}
	fclose(f);
}
//ham in do thi
void indothi(struct dothi g){
	struct Dske *p;
	printf("so dinh :%d\n",g.sodinh);
	printf("cac ds ke:\n");
	int i;
	for(i=0;i<g.sodinh;i++){
		printf("danh sach ke thu %d:",i);
		p=g.dske[i];
		while(p!=NULL){
			printf("%d",p->dinhke);
			p=p->link;
		}
		printf("\n");
	}
}
int datham[max];
int truoc[max];

//duyet sau
void duyetsau(struct dothi g,int v){
	int w;
	struct Dske *p;
	printf("%d",v);
	datham[v]=1;
	p=g.dske[v];
	while(p!=NULL){
		w=p->dinhke;
		if(datham[w]==0){
			truoc[w]=v;
			duyetsau(g,w);
		}
		p=p->link;
	}
}
//cài đặt thuật toán tìm đường đi
int duongdi(struct dothi g,int x,int y){
	int i;
	for(i=0;i<g.sodinh;i++){
		datham[i]=0;
		truoc[i]=0;
	}
	duyetsau(g,x);
	if(datham[y]==1){
		return 1;
	}
	else{
		return 0;
	}
}
void induongdi(int x,int y){
	printf("duong di tu %d den %d:",x,y);
	int z=y;
	while(z!=x){
		printf("%d<--",z);
		z=truoc[z];
	}
	printf("%d",x);
}
//tim cac thanh phan lien thong cua do thi
void timthanhphanlienthong(struct dothi g){
	int i,thanhphan=0;
	for(i=0;i<g.sodinh;i++){
		datham[i]=0;
	}
	printf("cac thanh phan lien thong:\n");
	for(i=0;i<g.sodinh;i++){
		if(datham[i]==0){
			printf("thanh phan thu %d:",thanhphan+1);
			duyetsau(g,i);
			printf("\n");
			thanhphan++;
		}
	}
	printf("so thanh phan lien thong: %d\n",thanhphan);
}
//duyetsaucaykhung
void duyetsauCK(struct dothi g,int v,struct dothi *h){
	struct Dske *p;
	int w;
	datham[v]=1;
	p=g.dske[v];
	while(p!=NULL){
		w=p->dinhke;
		if(datham[w]==0){
			them(v,w,h);
			them(w,v,h);
			duyetsauCK(g,w,h);
		}
		p=p->link;
	}
}
//tim mot cay khung
struct dothi caykhung(struct dothi g){
	struct dothi h;
	int i;
	h.sodinh=g.sodinh;
	for(i=0;i<g.sodinh;i++){
		h.dske[i]=NULL;
		datham[i]=0;
	}
	duyetsauCK(g,0,&h);
	return h;
}

void main(){
	struct dothi g,h;
	int x,y;
	doctep("DT2.txt",&g);
	indothi(g);
	printf("Nhap dinh x: ");
	scanf("%d",&x);
	printf("Nhap dinh y: ");
	scanf("%d",&y);
	printf("duyet sau dinh :%d",x);
	printf("\n");
	duyetsau(g,x);
	printf("\n");
	induongdi(x,y);
	printf("\n");
	timthanhphanlienthong(g);
	printf("cay khung cua do thi:\n");
	h=caykhung(g);
	indothi(h);
}
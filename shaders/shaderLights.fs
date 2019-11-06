#version 120


varying vec2 tex_coords;


uniform vec2 scale; //scale is the size of the window.

uniform int type;



uniform int size;
uniform vec4[500] location;  //light locations
uniform vec3 ambient;

uniform vec2[300] shadowPoints;
uniform int shadowPointsSize;



//this function maps values.
float map(float value, float min1, float max1, float min2, float max2){
	float perc = (value - min1) / (max1 - min1);

	// Do the same operation backwards with min2 and max2
	return perc * (max2 - min2) + min2;
}





//use in lineIntersection
float det(float a,float b,float c,float d){
	return a*d-b*c;
}

bool lineIntersection(float x1,float y1,float x2,float y2, float x3,float y3, float x4,float y4){
	if(x1-x2==0){
		float xdif=x4-x3;
		float ydif=y4-y3;
		float k=ydif/xdif;
		float m=-k*x3+y3;
		float y=k*x2+m;
		
		if(y>min(y1,y2) && y<max(y1,y2) && x1>=min(x3,x4) && x1<=max(x3,x4)){
			return true;
		}
		else{
			return false;
		}
	}
	
	float det1And2=det(x1,y1,x2,y2);
	float det3And4=det(x3,y3,x4,y4);
	float x1LessX2=x1-x2;
	float y1LessY2=y1-y2;
	float x3LessX4=x3-x4;
	float y3LessY4=y3-y4;
	float det1Less2And3Less4=det(x1LessX2,y1LessY2,x3LessX4,y3LessY4);
	
	if(det1Less2And3Less4==0){
		return false;
	}
	
	float lx=(det(det1And2,x1LessX2,det3And4,x3LessX4) / det1Less2And3Less4);
	float ly=(det(det1And2,y1LessY2,det3And4,y3LessY4) / det1Less2And3Less4);
	
	if(lx>=min(x1,x2) && lx>=min(x3,x4) && lx<=max(x1,x2) && lx<=max(x3,x4)){
		return true;
	}
	
	return false;
	
	
	
}

vec2 lineIntersection2(float x1,float y1,float x2,float y2, float x3,float y3, float x4,float y4){
	if(x1-x2==0){
		float xdif=x4-x3;
		float ydif=y4-y3;
		float k=ydif/xdif;
		float m=-k*x3+y3;
		float y=k*x2+m;
		
		if(y>min(y1,y2) && y<max(y1,y2) && x1>=min(x3,x4) && x1<=max(x3,x4)){
			return vec2(x1,y);
		}
		else{
			return vec2(1000,1000);
		}
	}
	
	float det1And2=det(x1,y1,x2,y2);
	float det3And4=det(x3,y3,x4,y4);
	float x1LessX2=x1-x2;
	float y1LessY2=y1-y2;
	float x3LessX4=x3-x4;
	float y3LessY4=y3-y4;
	float det1Less2And3Less4=det(x1LessX2,y1LessY2,x3LessX4,y3LessY4);
	
	if(det1Less2And3Less4==0){
		return vec2(1000,1000);
	}
	
	float lx=(det(det1And2,x1LessX2,det3And4,x3LessX4) / det1Less2And3Less4);
	float ly=(det(det1And2,y1LessY2,det3And4,y3LessY4) / det1Less2And3Less4);
	
	if(lx>=min(x1,x2) && lx>=min(x3,x4) && lx<=max(x1,x2) && lx<=max(x3,x4)){
		return vec2(lx,ly);
	}
	
	return vec2(1000,1000);
	
	
	
}






void main(){
	
	
	vec2 l = gl_FragCoord.xy;
	
	l.x=(l.x)/(scale.y/2);
	l.y=(l.y-scale.y/2)/(scale.y/2);
	
	
	
	float darkness;
	if(type==0){
		
		darkness=1;
		for(int i=0;i<size;i++){
			float xdif=location[i].x-l.x;
			float ydif=location[i].y-l.y;
			float dis=((xdif*xdif)+(ydif*ydif)+(location[i].z*location[i].z));
			bool inShadow=false;
			if(dis<100){
				for(int ii=0;ii<shadowPointsSize;ii+=2){
					vec2 point=lineIntersection2(shadowPoints[ii].x, shadowPoints[ii].y, shadowPoints[ii+1].x, shadowPoints[ii+1].y, l.x, l.y, location[i].x, location[i].y);
					if(point.x!=1000 && point.y!=1000){
						float xdif2=location[i].x-point.x;
						float ydif2=location[i].y-point.y;
						float dis2=sqrt((xdif2*xdif2)+(ydif2*ydif2));
						
						float xdif3=l.x-point.x;
						float ydif3=l.y-point.y;
						float dis3=sqrt((xdif3*xdif3)+(ydif3*ydif3));
						if(dis3*(location[i].z/8)<dis2){ //calculateing if it is in shadow or not.
							inShadow=true;
							break;
						}
					}
					
					
				}
			}
			if(inShadow==false){
				darkness-=1/((dis/location[i].w*(1/location[i].z)));  //calulate amount of light.
			}
			
		}
		
		
		gl_FragColor = vec4(0+ambient.x,0+ambient.y,0+ambient.z,darkness);
		
		
	}
	
	
	
	
	
		else if(type==1){
		
		float light=0;
		for(int i=0;i<size;i++){
			float xdif=location[i].x-l.x;
			float ydif=location[i].y-l.y;
			float dis=((xdif*xdif)+(ydif*ydif)+(location[i].z*location[i].z));
			bool inShadow=false;
			if(dis<100){
				for(int ii=0;ii<shadowPointsSize;ii+=2){
					vec2 point=lineIntersection2(shadowPoints[ii].x, shadowPoints[ii].y, shadowPoints[ii+1].x, shadowPoints[ii+1].y, l.x, l.y, location[i].x, location[i].y);
					if(point.x!=1000 && point.y!=1000){
						float xdif2=location[i].x-point.x;
						float ydif2=location[i].y-point.y;
						float dis2=sqrt((xdif2*xdif2)+(ydif2*ydif2));
						
						float xdif3=l.x-point.x;
						float ydif3=l.y-point.y;
						float dis3=sqrt((xdif3*xdif3)+(ydif3*ydif3));
						if(dis3*(location[i].z/800)<dis2){ //calculateing if it is in shadow or not.
							inShadow=true;
							break;
						}
					}
					
					
				}
			}
			if(inShadow==false){
				light+=1/((dis/location[i].w*(1/location[i].z)));  //calulate amount of light.
			}
			
		}
		
		
		gl_FragColor = vec4(1-ambient.x,1-ambient.y,1-ambient.z,light);
		
		
	}
	
	
	

}



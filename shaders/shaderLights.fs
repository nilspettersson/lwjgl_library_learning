#version 120


varying vec2 tex_coords;




uniform int size;
uniform vec4[100] location;

uniform vec2[100] shadowPoints;
uniform int shadowPointsSize;




float map(float value, float min1, float max1, float min2, float max2){
	float perc = (value - min1) / (max1 - min1);

	// Do the same operation backwards with min2 and max2
	return perc * (max2 - min2) + min2;
}






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







void main(){
	
	
	vec2 l = gl_FragCoord.xy;
	
	l.x=(l.x)/(1080/2);
	l.y=(l.y-1080/2)/(1080/2);
	
	
	float darkness=1;
	for(int i=0;i<size;i++){
		float xdif=location[i].x-l.x;
		float ydif=location[i].y-l.y;
		float dis=((xdif*xdif)+(ydif*ydif));
		bool inShadow=false;
		for(int ii=0;ii<shadowPointsSize;ii+=2){
			if(lineIntersection(shadowPoints[ii].x, shadowPoints[ii].y, shadowPoints[ii+1].x, shadowPoints[ii+1].y, l.x, l.y, location[i].x, location[i].y)){
				inShadow=true;
			}
		}
		if(inShadow==false){
			darkness-=(1/(sqrt(sqrt(dis))*0.5))/(10);
		}
		
	}
	
	float extra;
	if(darkness<0){
		extra-=darkness;
		gl_FragColor = vec4(1,1,1,(extra-4)/10);
	}
	else{
		gl_FragColor = vec4(0,0,0,darkness);
	}
	
	//gl_FragColor = vec4(0+extra*2,0+extra*2,0+extra*2,darkness+extra*2);
	
	
	
	
	
	
	/*if(lineIntersection(0.4, 0.2, 0.5, 0.5, l.x, l.y, location.x, location.y) || lineIntersection(0.7, 0.5, 0.9, 0.8, l.x, l.y, location.x, location.y)){
		gl_FragColor = vec4(0,0,0,0);
	}
	else{
		gl_FragColor = vec4(light,light/2,0.2,light);
	}*/

}



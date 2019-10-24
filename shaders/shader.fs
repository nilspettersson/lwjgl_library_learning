#version 120


varying vec2 tex_coords;

uniform float x;
uniform float y;

uniform float lsx1;
uniform float lsy1;
uniform float lex1;
uniform float ley1;

float random (vec2 st) {
    return fract(sin(dot(st.xy,
                         vec2(12.9898,78.233)))*
        43758.5453123*x);
}


float map(float value, float min1, float max1, float min2, float max2){
	float perc = (value - min1) / (max1 - min1);

	// Do the same operation backwards with min2 and max2
	return perc * (max2 - min2) + min2;
}






float det(float a,float b,float c,float d){
	return a*d-b*c;
}

bool lineIntersection(float x1,float y1,float x2,float y2, float x3,float y3, float x4,float y4){
	
	
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
	
	
	
	vec2 l = gl_FragCoord.xy/1080;
	
	
	/*float newX=map(x,0,1919,0,1);
	float newY=map(y,0,1079,0,1);
	gl_FragColor = vec4(newX,newY,(newX+newY)/2,1);*/
	
	
	
	/*float xx=map(x,0,1920,-1,1)+0.8;
	float yy=map(y,0,1080,1,-1)+0.5;*/
	
	
	
	float xdif=x-(l.x);
	float ydif=y-(l.y);
	float dis=sqrt((xdif*xdif)+(ydif*ydif));
	float light=1/(dis*10);
	//float light=(1/sqrt(dis-sin(cos(l.y*50)+sin(l.x*50))*10*sin(l.x*l.y*20)));
	//float a=1/sqrt(dis-sin(cos(l.y*20)+sin(l.x*20)));
	//float light=sin(a)/(dis*10*sin(l.x*2*l.y*2*a*cos(a*1))*l.x*l.y);
	
	
	
	
	
	if(lineIntersection(0.4, 0.2, 0.5, 0.5, l.x, l.y, x, y) || lineIntersection(0.7, 0.5, 0.9, 0.8, l.x, l.y, x, y)){
		gl_FragColor = vec4(0,0,0,0);
	}
	else{
		gl_FragColor = vec4(light,light/2,0.2,light);
	}

}



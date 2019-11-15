#version 120


varying vec2 tex_coords;

uniform float scale;

uniform float xPos;
uniform float yPos;
uniform int maxI;

float map(float value, float min1, float max1, float min2, float max2){
	float perc = (value - min1) / (max1 - min1);

	// Do the same operation backwards with min2 and max2
	return perc * (max2 - min2) + min2;
}


void main(){
	
	
	vec2 l = gl_FragCoord.xy;
	l.x-=1920/2;
	l.y-=1080/2;
	
	l.x/=scale;
	l.y/=scale;
	
	l.x+=xPos;
	l.y+=yPos;

	
	float x0=l.x;
	float y0=l.y;
	
	float x=0.0;
	float y=0.0;
	int i=0;

	while(x*x+y*y<=2*2 && i<maxI){

		float xTemp=x*x-y*y+x0;
		y=2*x*y+y0;
		x=xTemp;
		i++;

	}
	if(i==maxI){
		gl_FragColor = vec4(0,0,0,1);
	}
	else{
		float c=map(i,1,maxI,0,10);
		gl_FragColor = vec4(c,c,0,1);
	}

}



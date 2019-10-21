#version 120


varying vec2 tex_coords;

uniform float x;
uniform float y;

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


void main(){
	
	
	
	vec2 l = gl_FragCoord.xy;
	
	gl_FragColor = vec4(1,0,0,1);

}



attribute vec4 a_Position ;

uniform mat4 u_Matrix;

varying vec4 v_Color;


void main(){

    gl_Position = u_Matrix * a_Position;

    v_Color = vec4(0.5, 0.3, 0.0, 1.0);
}
attribute vec4 vPosition;

void main(){
    //gl_Position是glsl的内置变量，记录顶点最终的位置 。
    gl_Position = vPosition;
}
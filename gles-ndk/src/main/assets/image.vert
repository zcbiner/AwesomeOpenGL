#version 300 es
precision mediump float;
in vec4 a_position;
in vec2 a_textureCoordinate;
out vec2 v_textureCoordinate;
void main() {
    gl_Position = a_position;
    v_textureCoordinate = a_textureCoordinate;
}
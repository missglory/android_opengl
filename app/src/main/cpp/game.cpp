//
// Created by root on 13.02.19.
//

#include "game.h"
#include "glwrapper.h"
#include <GLES2/gl2.h>
#include <iostream>
void on_surface_created() {
    glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
//    std::cout << "hwa\n";
}

void on_surface_changed() {
    // No-op
//    std::cout << "hwc\n";
}

void on_draw_frame() {
    glClear(GL_COLOR_BUFFER_BIT);
//    std::cout << "hww\n";
}
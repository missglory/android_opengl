# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall")
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14 -fno-rtti -fno-exceptions -Wall")

message("-")
message("SRC DIR " ${CMAKE_CURRENT_SOURCE_DIR})
message("ANDROID_PLATFORM_LEVEL " ${ANDROID_PLATFORM_LEVEL})

if (${ANDROID_PLATFORM_LEVEL} LESS 12)
    mesage(FATAL_ERROR "OpenGL 2 is not supported before API level 11 \
                      (currently using ${ANDROID_PLATFORM_LEVEL}).")
    return()
elseif (${ANDROID_PLATFORM_LEVEL} LESS 18)
    add_definitions("-DDYNAMIC_ES3")
    set(GL3STUB_SRC gl3stub.c)
    set(OPENGL_LIB GLESv2)
else ()
    set(OPENGL_LIB GLESv3)
endif (${ANDROID_PLATFORM_LEVEL} LESS 12)


add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp
        )

add_library(game
            SHARED
            jni.cpp
            game.cpp
        )


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
message("FIND LIBS:")
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )
message("log-lib " ${log-lib})

find_library(gl-lib
             ${OPENGL_LIB})
message("gl-lib " ${gl-lib})
find_library(glesv3
        GLESv3)
message("GLESv3 LIB: " ${glesv3})
message("----------------------------------")


# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
message("OPENGLLIB " ${OPENGL_LIB})
target_link_libraries( # Specifies the target library.
                       game
                       ${gl-lib}
                       android
                       EGL
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )


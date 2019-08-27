# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

file(GLOB SOURCE src/main/cpp/*.cpp)

add_library(
        native-lib
        SHARED
        ${SOURCE}

)

set(my_lib_path ${CMAKE_SOURCE_DIR}/src/main/cpp/libs/${ANDROID_ABI})
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${my_lib_path} ")

include_directories(src/main/cpp/include/)
target_link_libraries(
        native-lib
        avfilter avformat  avcodec  avutil  swresample swscale
        z
        OpenSLES
        android
        log)
//generate hello_world.dll using:
//gcc -shared -o hello_world.dll hello_world.c                 for windows
//gcc -shared -fPIC -o libhello_world.so hello_world.c         for linux
//clang -dynamiclib -o libhello_world.dylib hello_world.c      for max
const char* hello_world() {
    return "Hello, World!";
}
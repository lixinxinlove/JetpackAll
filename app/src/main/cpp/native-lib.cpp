#include <jni.h>
#include <string>
#include "md5-lib.cpp"

using namespace std;

extern "C" JNIEXPORT jstring
Java_com_lixinxinlove_all_activity_CppActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    string hello = "Hello from C++";
    string md5 = "gdfgdgdggg";


    return env->NewStringUTF(md5.c_str());
}

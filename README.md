#fannj

Java bindings to [FANN](http://leenissen.dk/fann), the Fast Artificial Neural Network C library.

##Overview
Use FannJ if you have an existing ANN from the FANN project that you would like to access from Java. There are several GUI tools that will help you create and train an ANN.

##Installation
Before using FannJ, you must build and install the FANN C library. FannJ has been tested on FANN 2.2.0. See the FANN site for instructions and help: http://leenissen.dk/fann

##Code Example
    Fann fann = new Fann( "/path/to/file" );
    float[] inputs = new float[]{ -1, 1 };
    float[] outputs = fann.run( inputs );
    fann.close();
  
##Dependencies
[FANN](http://leenissen.dk/fann) - Does all the work.

[JNA](https://github.com/twall/jna) - Provides the native access to FANN.
   
##Maven 2 Support
This project is now in the Maven Central Repository. If you use Maven 2 for your builds, here is the stuff to put in your pom.xml

    <dependencies>
        <dependency>
            <groupId>com.googlecode.fannj</groupId>
            <artifactId>fannj</artifactId>
            <version>0.7</version>
        </dependency>
    </dependencies>

##Running
JNA provides the binding from Java to the FANN C library via JNI. You must set the jna.library.path system property to the path to the FANN Library. This property is similar to java.library.path but only applies to libraries loaded by JNA. You should also change the appropriate library access environment variable before launching the VM. This is PATH on Windows, LD\_LIBRARY\_PATH on Linux, and DYLD\_LIBRARY\_PATH on OSX.

Linux something like:
    
    LD_LIBRARY_PATH=/usr/local/lib

    java -Djna.library.path=/usr/local/lib -cp fannj-0.7.jar:jna-3.2.2.jar YourClass

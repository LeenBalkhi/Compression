# Compression

A Java-based file compression and decompression tool utilizing Huffman Coding and LZW Algorithm.

## Overview

This project implements a simple file compression and decompression utility using the Huffman Coding algorithm. It allows users to reduce file sizes for storage efficiency and decompress them back to their original form.

## Features

* Compresses text files using Huffman Coding and LZW
* Decompresses previously compressed files
* Command-line interface for ease of use
* Written in Java

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or higher

### Compilation

To compile the project, navigate to the project's root directory and run:

```
javac -d out/production/Compression src/*.java
```

### Usage

After compilation, you can run the program using:

```
java -cp out/production/Compression Main
```

Follow the on-screen prompts to compress or decompress files.

## Project Structure

* `src/` - Contains the Java source code files
* `out/production/Compression/` - Directory for compiled `.class` files
* `.idea/` - IntelliJ IDEA project configuration files
* `Compression.iml` - IntelliJ IDEA module file

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License

This project is open-source and available under the MIT License.

## PKEPVDD_DEMO

### Abstract

We introduce a new cryptographic primitive called public key encryption with public-verifiable decryption delegation ($PKE-PVD^2$), that enables the original decryptor to transmit the decryption key for a specific ciphertext to a designated recipient in a way that is both public-verifiable and privacy-preserving. Because of its ability to delegate public-verifiable and ciphertext-level decryption, it is useful in scenarios that require special decryption delegation, such as when the court needs to retrieve a specific message from the ciphertext as significant judgment evidence. Moreover, we present the first $PKE-PVD^2$ scheme and its corresponding security proof in the random oracle model, using bilinear pairing. Additionally, we provide a prototype of our proposal, and our extensive experimental results demonstrate its effectiveness and efficiency.

### Introduction

A simple demo to simulate users communicating with each other via the internet in an encrypted way, where the user delegates the decryption right to the court for a specific ciphertext.

### Usage

- Install Java 9.0.4
- Install dependencies 
  - jpbc-api-2.0.0.jar
  - jpbc-plaf-2.0.0.jar

### Directory Description

- src/main/java/entities: This directory stores the code about the entities of users and the court.

- src/main/java/tools: This directory stores the related cryptographic tools, such as bilinear pairing, Sha256, and ECDSA on curve Secp256k1.

- DKTMain.java: The user can execute the whole project by running this file.

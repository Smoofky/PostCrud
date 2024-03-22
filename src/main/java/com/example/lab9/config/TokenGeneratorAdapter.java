/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.lab9.config;

/**
 *
 * @author rytel
 */
public interface TokenGeneratorAdapter {
    
    String generateTokenSHA256(String username);
    String generateTokenSHA512(String username);
    String generateTokenSHA384(String username);

}

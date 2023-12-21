//
//  AuthorizationView.swift
//  iosApp
//
//  Created by Олег Романов on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthorizationView: View {
    
    @State private var username = ""
    @State private var password = ""
    
    @Binding var isRegistrationActive: Bool
    
    var body: some View {
        GeometryReader { geometry in
            ZStack {
                Color.clear
                VStack {
                    Text("Вход в аккаунт")
                        .bold()
                        .font(.largeTitle)
                    
                    TextField("Логин", text: $username)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    SecureField("Пароль", text: $password)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    Button(
                        action: {
                            // TODO: Authorization logic
                        },
                        label: {
                            Text("Войти")
                                .font(.system(size: 20))
                                .frame(width: geometry.size.width - 32, height: 54)
                                .foregroundStyle(.white)
                                .background(Color.blue)
                                .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                        }).padding(.vertical, 16)
                    
                    
                    HStack {
                        Text("Нет аккаунта?")
                        Button {
                            isRegistrationActive = true
                        } label: {
                            Text("Создать")
                                .foregroundStyle(.blue)
                        }
                    }
                }
            }
        }
    }
}

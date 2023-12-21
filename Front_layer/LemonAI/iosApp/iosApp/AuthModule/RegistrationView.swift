//
//  RegistrationView.swift
//  iosApp
//
//  Created by Олег Романов on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationView: View {
    @State private var firstName = ""
    @State private var lastName = ""
    @State private var username = ""
    @State private var password = ""
    
    @Binding var isRegistrationActive: Bool
    
    var body: some View {
        GeometryReader { geometry in
            ZStack {
                Color.clear
                VStack {
                    Text("Регистрация")
                        .bold()
                        .font(.largeTitle)
                    
                    TextField("Имя", text: $firstName)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    TextField("Фамилия", text: $lastName)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    TextField("Логин", text: $username)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    SecureField("Пароль", text: $password)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(EdgeInsets(top: 4, leading: 16, bottom: 4, trailing: 16))
                    
                    Button(
                        action: {
                            // TODO: Registration logic
                        },
                        label: {
                            Text("Зарегистрироваться")
                                .font(.system(size: 20))
                                .frame(width: geometry.size.width - 32, height: 54)
                                .foregroundStyle(.white)
                                .background(Color.blue)
                                .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                        }
                    ).padding(.vertical, 16)
                    
                    HStack {
                        Text("Уже есть аккаунт?")
                        Button {
                            isRegistrationActive = false
                        } label: {
                            Text("Войти")
                                .foregroundStyle(.blue)
                        }
                    }
                }
            }
        }
    }
}

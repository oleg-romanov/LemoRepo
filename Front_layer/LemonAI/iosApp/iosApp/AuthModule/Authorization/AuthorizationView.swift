//
//  AuthorizationView.swift
//  iosApp
//
//  Created by Олег Романов on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AuthorizationView: View {
    
    @EnvironmentObject var appState: AppState
    
    @ObservedObject var viewModel = AuthorizationViewModel()
    
    var body: some View {
        NavigationStack {
            ZStack {
                Color.clear
                VStack(spacing: 16) {
                    Text("Вход в аккаунт")
                        .bold()
                        .font(.largeTitle)
                    
                    InputView(
                        title: "Логин",
                        text: $viewModel.credentials.username,
                        placeholder: "Введите ваш логин")
                    
                    InputView(
                        title: "Пароль",
                        text: $viewModel.credentials.password,
                        placeholder: "Введите ваш пароль",
                        isSecureField: true
                    )
                    
                    Button(
                        action: {
                            if (!viewModel.credentials.username.isEmpty && !viewModel.credentials.password.isEmpty) {
                                Task {
                                    try await viewModel.performAuth()
                                    DispatchQueue.main.async {
                                        if (viewModel.authorizationIsSuccess) {
                                            appState.hasAuthorized = true
                                        }
                                    }
                                }
                                
                            } else {
                                Alert(
                                    title: Text("Не заполнены обязательные поля"),
                                    message: Text("Пожалуйста, заполните все поля"),
                                    dismissButton: .default(Text("OK"))
                                )
                            }
                        },
                        label: {
                            Text("Войти")
                                .font(.system(size: 20))
                                .fontWeight(.semibold)
                                .frame(width: UIScreen.main.bounds.width - 32, height: 54)
                                .foregroundStyle(.white)
                                .background(Color.blue)
                                .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                        }
                    )
                    
                    HStack(spacing: 4) {
                        Text("Нет аккаунта?")
                        NavigationLink {
                            RegistrationView()
                                .navigationBarBackButtonHidden(true)
                        } label: {
                            Text("Создать")
                                .fontWeight(.bold)
                        }
                        .font(.system(size: 16))
                    }
                }
                .padding(.horizontal)
            }
        }
    }
}

struct AuthorizationView_Previews: PreviewProvider {
    static var previews: some View {
        AuthorizationView()
    }
}

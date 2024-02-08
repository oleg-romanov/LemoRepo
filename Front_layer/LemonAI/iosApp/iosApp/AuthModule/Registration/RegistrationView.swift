//
//  RegistrationView.swift
//  iosApp
//
//  Created by Олег Романов on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationView: View {
    @Environment(\.dismiss) private var dismiss
    
    @ObservedObject var viewModel = RegistrationViewModel()
    
    var body: some View {
        ZStack {
            Color.clear
            
            VStack(spacing: 16) {
                Text("Регистрация")
                    .bold()
                    .font(.largeTitle)
                
                InputView(
                    title: "Имя",
                    text: $viewModel.credentials.firstName,
                    placeholder: "Имя")
                
                InputView(
                    title: "Фамилия",
                    text: $viewModel.credentials.lastName,
                    placeholder: "Фамилия")
                
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
                
                Button {
                    if (!viewModel.credentials.firstName.isEmpty && !viewModel.credentials.lastName.isEmpty && !viewModel.credentials.username.isEmpty && !viewModel.credentials.password.isEmpty) {
                        Task {
                            try await viewModel.performRegistration()
                        }
                    } else {
                        
                    }
                    // TODO: Registration logic
                    
                } label: {
                    Text("Зарегистрироваться")
                        .font(.system(size: 20))
                        .fontWeight(.semibold)
                        .frame(width: UIScreen.main.bounds.width - 32, height: 54)
                        .foregroundStyle(.white)
                        .background(Color.blue)
                        .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                }
                
                
                HStack(spacing: 4) {
                    Text("Уже есть аккаунт?")
                    Button {
                        dismiss()
                    } label: {
                        Text("Войти")
                            .foregroundStyle(.blue)
                            .fontWeight(.bold)
                    }
                }
                .font(.system(size: 16))
            }
            .padding(.horizontal)
        }.alert(isPresented: $viewModel.registrationIsSuccess) {
            Alert(
                title: Text("Вы успешно зарегестрированы"),
                message: Text("Пожалуйста войдите в Ваш аккаунт"),
                dismissButton: .default(Text("OK"))
            )
        }
    }
}

struct RegistrationView_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationView()
    }
}

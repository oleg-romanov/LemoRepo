//
//  ProfileView.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    @State private var firstName = ""
    @State private var lastName = ""
    @State private var username = ""
    
    
    var body: some View {
        GeometryReader { geometry in
            NavigationView {
                VStack(alignment: .center) {
                    Image(systemName: "person.circle")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: geometry.size.width / 2, height: geometry.size.width / 2)
                        .padding(.top, 20)
                        .foregroundStyle(.gray)
                    
                    let userInfo = obtainUserInfo()
                    
                    VStack(alignment: .leading, spacing: 16) {
                        Text("Имя: \(userInfo.firstName)")
                            .font(.system(size: 20))
                        Text("Фамилия: \(userInfo.lastName)")
                            .font(.system(size: 20))
                        Text("Логин: \(userInfo.userName)")
                            .font(.system(size: 20))
                    }
                    .padding(.top, 16)
                    
                    Spacer()
                    
                    Button(action: {
                        // TODO: Logout logic
                    }, label: {
                        Label {
                            Text("Выйти из аккаунта")
                                .font(.system(size: 20))
                        } icon: {
                            Image(systemName: "arrow.left.circle.fill")
                                .foregroundStyle(.white)
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 54)
                        .background(.red)
                        .foregroundStyle(.white)
                        .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                    })
                    .padding(.horizontal, 16)
                    .padding(.bottom, 24)
                    .navigationTitle("Профиль")
                    .navigationBarTitleDisplayMode(.inline)
                }
            }
            
        }
    }
    
    private func obtainUserInfo() -> ProfileModel {
        // Mock obtain user data
        return ProfileModel(firstName: "John", lastName: "Doe", userName: "johndoe")
    }
}

//
//  AuthorizationViewModel.swift
//  iosApp
//
//  Created by Олег Романов on 05.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class AuthorizationViewModel: ObservableObject {
    @Published var credentials = AuthModel(username: "", password: "")
    @Published var authorizationIsSuccess: Bool = false
    
    func performAuth() async throws {
        let user: UserLogin = UserLogin(Login: credentials.username, Password: credentials.password)
        
        let result = try await ServerAdapterKt.httpGetUser(userLogin: user)
        guard let result = result as? Bool else {
            fatalError()
        }
        DispatchQueue.main.async { [weak self] in
            self?.authorizationIsSuccess = result
        }
    }
}

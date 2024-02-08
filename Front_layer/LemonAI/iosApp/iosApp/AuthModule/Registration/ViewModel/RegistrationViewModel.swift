//
//  RegistrationViewModel.swift
//  iosApp
//
//  Created by Олег Романов on 03.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class RegistrationViewModel: ObservableObject {
    
    @Published var credentials = RegistrationModel(firstName: "", lastName: "", username: "", password: "")
    @Published var registrationIsSuccess: Bool = false
    
    func performRegistration() async throws {
        let user: User = User(
            Login: credentials.username,
            Password: credentials.password,
            Name: credentials.firstName,
            Surname: credentials.lastName
        )
        let result = try await ServerAdapterKt.httpAddUser(user: user)
        print(user)
        guard let result = result as? Bool else {
            fatalError()
        }
        print(result)
        DispatchQueue.main.async { [weak self] in
            self?.registrationIsSuccess = result
        }
    }
}

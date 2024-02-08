//
//  InputView.swift
//  iosApp
//
//  Created by Олег Романов on 04.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct InputView: View {
    let title: String
    @Binding var text: String
    let placeholder: String
    var isSecureField = false
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(title)
                .foregroundStyle(Color(.darkGray))
                .fontWeight(.semibold)
//                .font(.footnote)
                .font(.system(size: 14))
            
            if isSecureField {
                SecureField(placeholder, text: $text)
                    .font(.system(size: 16))
            } else {
                TextField(placeholder, text: $text)
                    .font(.system(size: 16))
            }
            
            Divider()
        }
    }
}

struct InputView_Previews: PreviewProvider {
    static var previews: some View {
        InputView(title: "Email Address", text: .constant(""), placeholder: "name@example.com")
    }
}

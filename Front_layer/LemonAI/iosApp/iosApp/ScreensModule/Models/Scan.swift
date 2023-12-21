//
//  Scan.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Scan: Identifiable {
    var id = UUID()
    var date: Date
    var storeName: String
    var result: String
}

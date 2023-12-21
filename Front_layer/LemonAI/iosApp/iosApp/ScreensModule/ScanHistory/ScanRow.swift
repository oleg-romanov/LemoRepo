//
//  ScanRow.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScanRow: View {
    
    var scan: Scan
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Дата: \(formattedDate)")
            Text("Магазин: \(scan.storeName)")
            Text("Результат: \(Text("\(scan.result)").foregroundColor(resultIsGood(result: scan.result) ? .green : .red))")
        }
    }
    
    private var formattedDate: String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd.MM.yyyy"
        return dateFormatter.string(from: scan.date)
    }
    
    private func resultIsGood(result: String) -> Bool {
        return result == "Хороший" ? true : false
    }
}

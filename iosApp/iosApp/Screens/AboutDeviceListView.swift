//
//  AboutDeviceListView.swift
//  iosApp
//
//  Created by Mohit Kansal on 10/07/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutDeviceListView: View {
    private struct RowItem: Hashable{
        let title: String
        let description: String
    }
    
    private let items : [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        var result : [RowItem] =
    [
        .init(title : "OS Name", description : platform.osName),
        .init(title : "OS Version", description : platform.osVersion),
        .init(title : "Device Details", description : platform.deviceModel)
    ]
        return result
    }()
    
    var body: some View {
        List{
            ForEach(items, id : \.self){ item in
                VStack(alignment: .leading){
                    Text(item.title)
                        .font(.footnote).foregroundStyle(.secondary)
                    Text(item.description)
                        .font(.body).foregroundStyle(.primary)
                }.padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutDeviceListView()
}

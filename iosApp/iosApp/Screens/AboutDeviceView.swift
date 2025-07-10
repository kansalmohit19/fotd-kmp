//
//  AboutDeviceView.swift
//  iosApp
//
//  Created by Mohit Kansal on 10/07/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutDeviceView: View {
    var body: some View {
        NavigationStack{
            AboutDeviceListView()
                .navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutDeviceView()
}

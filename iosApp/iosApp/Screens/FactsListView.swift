//
//  FactsListView.swift
//  iosApp
//
//  Created by Mohit Kansal on 10/07/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

extension FactsListView{

    @MainActor
    class FactsListViewModelWrapper: ObservableObject{
        let factsListViewModel: FactsListViewModel
        @Published var factsListState: FactListState

        init() {
            factsListViewModel = FactsListInjector().factsListViewModel
            factsListState = factsListViewModel.factsListFlow.value as! FactListState
        }

        /*func startObserving() {
            Task {
                
                    for  await state in factsListViewModel.factsListFlow {
                        self.factsListState = state
                    }
            }
        }*/
        func startObserving() {
                factsListViewModel.observeFacts { state in
                    DispatchQueue.main.async {
                        NSLog("New state: \(String(describing: state))")
                        self.factsListState = state
                    }
                }
            }
    }
}

struct FactsListView: View {
    @ObservedObject private(set) var viewModel: FactsListViewModelWrapper

    var body: some View {
        VStack {
            AppBar()
            if viewModel.factsListState.isLoading {
                Loader()
            } else if let error = viewModel.factsListState.errorMessage {
                ErrorView(message: error)
            } else if !viewModel.factsListState.listOfFacts.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.factsListState.listOfFacts, id: \.self) { fact in
                            FactRowView(fact: fact)
                        }
                    }
                }
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
    }
}

struct AppBar: View{
    var body: some View{
        Text("Facts List").font(.largeTitle).fontWeight(.bold)
    }
}

struct Loader: View{
    var body: some View{
        ProgressView()
    }
}

struct ErrorView: View{
    var message: String
    var body: some View{
        Text(message).font(.title)
    }
}

struct FactRowView: View {
    var fact: FactDetails

    var body: some View{
        VStack(alignment: .leading, spacing: 8){
            AsyncImage(url: URL(string: fact.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image?.resizable().aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(fact.titleText).font(.title).fontWeight(.bold)
            Text(fact.descriptionText)
            Text(fact.postedOnDate).frame(maxWidth: .infinity,alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }

}

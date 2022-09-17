import 'package:anime_app_flutter/constants/constants.dart';
import 'package:anime_app_flutter/service/dio_service.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../model/anime_quote_model.dart';

class MainView extends StatefulWidget {
  const MainView({Key? key}) : super(key: key);

  @override
  _MainViewState createState() => _MainViewState();
}

class _MainViewState extends State<MainView> {
  List<AnimeQuoteModel>? _animeQuoteList;
  //bool _isLoading = false;
  late final IAnimeQuoteService _animeQuoteService;

  @override
  void initState() {
    super.initState();
    _animeQuoteService = DioService();
  }

  Future<void> getAnimeQuotes() async {
    //_changeIsLoading();
    final response = await _animeQuoteService
        .getAnimeQuotes(); //DioService().getAnimeQuotes();
    if (response != null) {
      setState(() {
        _animeQuoteList = response; //.cast<AnimeQuoteModel>()
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //appBar: AppBar(),
      body: Column(
        children: [
          _animeQuoteList == null ? const Placeholder() : buildList(),
          ElevatedButton(
              onPressed: () {
                getAnimeQuotes();
              },
              style: ButtonStyle(
                  backgroundColor:
                      MaterialStateProperty.all(ButtonDetails.buttonColor)),
              child: const Text(ButtonDetails.buttonText))
        ],
      ),
    );
  }

  Expanded buildList() {
    return Expanded(
      child: ListView.builder(
          itemCount: _animeQuoteList?.length ?? 0,
          itemBuilder: (context, index) {
            return Card(
                child: Container(
              decoration: buildBoxDecoration(),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Image.network(
                    Constants.animeImageUrl,
                    height: ImageSize.size,
                    width: ImageSize.size,
                  ),
                  GestureDetector(
                    onTap: () {
                      Fluttertoast.showToast(
                        msg: _animeQuoteList?[index].quote ?? "Empty",
                        toastLength: Toast.LENGTH_SHORT,
                        gravity: ToastGravity.BOTTOM,
                        backgroundColor: Colors.white,
                        textColor: Colors.black,
                      );
                    },
                    child: SizedBox(
                      width: MediaQuery.of(context).size.width - 120,
                      child: Column(
                        children: [
                          buildText(
                              _animeQuoteList?[index].anime ?? "", index, 1),
                          buildText(_animeQuoteList?[index].character ?? "",
                              index, 1),
                          buildText(
                              _animeQuoteList?[index].quote ?? "", index, 2),
                        ],
                      ),
                    ),
                  )
                ],
              ),
            ));
          }),
    );
  }

  BoxDecoration buildBoxDecoration() {
    return const BoxDecoration(
        color: Colors.white,
        image: DecorationImage(
            image: AssetImage(ImagePath.imagePath), fit: BoxFit.cover));
  }

  Text buildText(String string, int index, int maxLines) {
    return Text(
      string,
      maxLines: maxLines,
      style: const TextStyle(color: TextDetails.color),
    );
  }

  // void _changeIsLoading() {
  //   setState(() {
  //     _isLoading = !_isLoading;
  //   });
  // }
}

class ImagePath {
  static const imagePath = "assets/png/background.png";
}

class ButtonDetails {
  static const buttonText = "Fetch Anime List";
  static const buttonColor = Color(0xFF6DB0FF);
}

class ImageSize {
  static const size = 100.0;
}

class TextDetails {
  static const color = Color(0xFFFFAA00);
}

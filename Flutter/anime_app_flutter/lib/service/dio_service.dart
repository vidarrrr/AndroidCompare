import 'package:anime_app_flutter/service/anime_quote_service.dart';
import 'package:dio/dio.dart';

import '../model/anime_quote_model.dart';

abstract class IAnimeQuoteService {
  Future<List<AnimeQuoteModel>?> getAnimeQuotes(Function(String?) param);
}

class DioService implements IAnimeQuoteService {
  @override
  Future<List<AnimeQuoteModel>?> getAnimeQuotes(
      Function(String?) loadDataWithErrorControl) async {
    final dio = Dio();
    final client = RestClient(dio);
    try {
      final response = await client.getAnimeQuoteList();
      return response;
    } on DioError catch (error) {
      loadDataWithErrorControl(error.message);
    }
    return null;
  }
}
